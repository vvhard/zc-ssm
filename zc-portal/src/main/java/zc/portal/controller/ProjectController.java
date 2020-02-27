package zc.portal.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import zc.commons.bean.AjaxResult;
import zc.commons.pojo.TMember;
import zc.commons.pojo.TProject;
import zc.portal.util.Configuration;
import zc.portal.util.Constant;
import zc.portal.util.HttpClientUtil;

import javax.servlet.http.HttpSession;

@Controller
public class ProjectController {
    @RequestMapping("/project")
    public String details(int id, HttpSession session){
        System.out.println(id);
        String json = HttpClientUtil.httpGetRequest(Configuration.remoteAddress + "/project/" + id);
        AjaxResult<TProject> result = JSON.toJavaObject(JSON.parseObject(json), AjaxResult.class);
        String p = JSON.toJSONString(result.getContent());
        TProject project = JSON.toJavaObject(JSON.parseObject(p), TProject.class);
        // 获取项目发布者信息
        String m = HttpClientUtil.httpGetRequest(Configuration.remoteAddress + "/project/member/" + project.getMemberid());
        AjaxResult<TMember> memberAjaxResult = JSON.toJavaObject(JSON.parseObject(m), AjaxResult.class);
        String mem = JSON.toJSONString(memberAjaxResult.getContent());
        TMember member = JSON.toJavaObject(JSON.parseObject(mem), TMember.class);
        session.setAttribute(Constant.PROJECT_MEMBER, member);
        session.setAttribute(Constant.PROJECT, project);
        return "project";
    }
}
