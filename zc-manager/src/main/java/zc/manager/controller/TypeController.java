package zc.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import zc.commons.pojo.TAccountTypeCert;
import zc.commons.pojo.TAcctType;
import zc.commons.pojo.TCert;
import zc.manager.AjaxResult;
import zc.manager.service.AcctTypeService;
import zc.manager.service.CertService;

import java.util.List;

/**
 * 项目资质管理
 */
@Controller
@RequestMapping("/serviceman/category")
public class TypeController {
    @Autowired
    private CertService certServiceImpl;
    @Autowired
    private AcctTypeService acctTypeServiceImpl;

    @RequestMapping("/index")
    public String index(Model model) {
        List<TCert> certs = certServiceImpl.getAllCerts();
        List<TAcctType> acctTypes = acctTypeServiceImpl.getAllAcctTypes();
        // 初始化设置每种账户类型拥有的资质都为空，设置为false
        for (TAcctType acctType : acctTypes) {
            // 查询账户类型需要的资质
            List<TCert> list = acctTypeServiceImpl.getAcctCertsByName(acctType.getName());
            for (TCert tCert : certs) {
                boolean flag = false; // 标记是否需要资质
                for (TCert cert : list) {
                    if (cert.getName().equals(tCert.getName())) {
                        flag = true;
                        break;
                    }
                }
                if (flag)
                    acctType.getCertList().add(true);
                else
                    acctType.getCertList().add(false);
            }
        }
        model.addAttribute("certs", certs);
        model.addAttribute("acctTypes", acctTypes);
        return "serviceman/type";
    }
    @ResponseBody
    @RequestMapping("/update")
    public Object typeUpdate(int accttypeid,int[] certids) {
        AjaxResult result = new AjaxResult();
        try {
            acctTypeServiceImpl.deleteAcctCertById(accttypeid);
        } catch (Exception e) {
            result.setSuccess(false);
            return result;
        }
        for (int certid : certids) {
            TAccountTypeCert tact = new TAccountTypeCert();
            tact.setAccttypeid(accttypeid);
            tact.setCertid(certid);
            try {
                acctTypeServiceImpl.upadteAcctCert(tact);
            } catch (Exception e) {
                result.setSuccess(false);
                e.printStackTrace();
                return result;
            }
        }
        result.setSuccess(true);
        return result;
    }
}
