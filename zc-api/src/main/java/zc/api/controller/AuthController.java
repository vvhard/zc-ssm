package zc.api.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import zc.api.service.MemberService;
import zc.commons.bean.AjaxResult;
import zc.commons.pojo.TAcctType;
import zc.commons.pojo.TCert;
import zc.commons.pojo.TMember;
import zc.commons.pojo.TMemberCert;
import zc.manager.service.AcctTypeService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private AcctTypeService acctTypeServiceImpl; // 不能依赖注入可能是配置文件没有配置扫描其他模块的包
    // @RequestMapping(value = "/",method = RequestMethod.GET)
    // 与上述注解等价,只拦截get请求
    @GetMapping(value = "/acct_type")
    public  AjaxResult<List<TAcctType>> getAcctTypes(){
        AjaxResult<List<TAcctType>> result ;
        Map<String,Object> ext = new HashMap<>();
        try {
            List<TAcctType> allAcctTypes = acctTypeServiceImpl.getAllAcctTypes();
            for(TAcctType type: allAcctTypes){
                type.setCerts(acctTypeServiceImpl.getAcctCertsByName(type.getName()));
            }
            result = AjaxResult.success("查询成功", allAcctTypes, null);
        } catch (Exception e) {
            ext.put("err", e.toString());
            result = AjaxResult.fail("查询数据库失败",null, ext);
        }
        return result;
    }
    @GetMapping(value = "/{acct_type}")
    public  AjaxResult<List<TCert>> getCerts(@PathVariable("acct_type")String acct_type){
        AjaxResult<List<TCert>> result ;
        Map<String,Object> ext = new HashMap<>();
        try{
            List<TCert> certs = acctTypeServiceImpl.getAcctCertsByName(acct_type);
            result = AjaxResult.success("查询成功", certs, null);
        }catch (Exception e){
            ext.put("err", "查询数据库出错");
            ext.put("exception", e.toString());
            result = AjaxResult.fail("查询失败",null,ext);
        }
        return result;
    }
//    @RequestMapping("/upload")
//    public Object test(){
//
//    }

    /**
     * 需要先将字符串(json)转换为原来的对象,file,member,certid
     * @param loginacct
     * @param file
     * @param member
     * @param validateCode
     * @param certid
     * @param session
     * @return
     */
    @RequestMapping("/upload")
    @ResponseBody
    public AjaxResult<TMember> uploadInfo(String loginacct,String file, String member, String validateCode, String certid,
                                          HttpSession session) {

        // 转换为原来的内容
        List<MultipartFile> files = JSON.parseArray(file,MultipartFile.class);
        TMember mem = JSON.toJavaObject(JSON.parseObject(member), TMember.class);
        List<Integer> cert_ids = JSON.parseArray(certid,Integer.class);

        System.out.println("file："+files);
        System.out.println("mem："+mem);
        System.out.println("certid："+cert_ids);

        AjaxResult<TMember> result;
        Map<String, Object> ext = new HashMap<>();
        if (file != null && files.size() > 0) {
            List<TMemberCert> list = new ArrayList<TMemberCert>();
            for (int i = 0; i < cert_ids.size(); i++) { // 遍历该用户的所需cert的id
                TMemberCert tmc = new TMemberCert();
                MultipartFile multipartFile = files.get(i); // 第i个文件
                String path = uploadFile("/auth_img", multipartFile, session); // 上传，返回在服务器中的地址
                System.out.println("服务器中的地址:" + path);
                tmc.setCertid(cert_ids.get(i));
                try{
                    tmc.setMemberid(memberService.queryId(loginacct));
                }catch (Exception e){
                    ext.put("err", "数据库操作失败");
                    ext.put("exception", e.toString());
                    result = AjaxResult.fail("信息提交及图片上传失败", null, ext);
                    return result;
                }
                tmc.setIconpath(path);
                list.add(tmc); // 资质记录
            }
            mem.setLoginacct(loginacct);
            try{
//                memberService.update(member); //更新信息
//                memberService.cert(list); // 保存带t_member_cert表，list 内容为tmc
                memberService.auth(mem,list); // 带事务
                result = AjaxResult.fail("信息提交及图片上传成功", mem, null);
            }catch (Exception e){
                ext.put("err", "数据库操作失败");
                ext.put("exception", e.toString());
                result = AjaxResult.fail("信息提交及图片上传失败", null, ext);
            }
            return result;
        }else{
            ext.put("err", "没有图片");
            result = AjaxResult.fail("信息提交及图片上传失败", null, ext);
            return result;
        }
    }
    /**
     * 上传
     */
    private String uploadFile(String webPath, MultipartFile file,HttpSession session) {
        ServletContext context = session.getServletContext();
        String realPath = context.getRealPath(webPath);
        System.out.println("真实地址:" + realPath);
        String filename = UUID.randomUUID().toString().replace("-", "").
                                 substring(0, 10) + "_file_" + file.getOriginalFilename();
        try {
            //webPath不存在的情况下必须创建
            File file2 = new File(realPath);
            if(!file2.exists()){
                //创建目录
                file2.mkdirs();
            }
            file.transferTo(new File(realPath+"/" + filename));
            return webPath+"/" + filename;
        } catch (Exception e) {
            return null;
        }
    }
}
