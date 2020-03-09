package zc.manager.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import zc.commons.pojo.TPermission;
import zc.commons.pojo.TUser;
import zc.manager.constant.MyConstant;
import zc.manager.service.PermissionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PermissionInterceptor implements HandlerInterceptor {
    @Autowired
    private PermissionService permissionService;
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        // 服务器地址
        String path = httpServletRequest.getSession().getServletContext().getContextPath();
        // 获取当前用户
        TUser user = (TUser)httpServletRequest.getSession().getAttribute(MyConstant.LOGIN_USER);
        // 登录拦截器已经执行，user不可能为空
        // 得到所有许可，即间接得到了所有需要进行授权才能访问的url
//        List<TPermission> permissions = permissionService.getAllPermission();
        // 得到用户可访问的url
        List<TPermission> permissions = (List<TPermission>) permissionService.getPermissons(user);
        // 进行处理，保存到set中
        Set<String> uriRegxSet = new HashSet<>(); // 保存所有需要授权的uri所匹配的正则表达式
        String uri = path + httpServletRequest.getRequestURI(); // 当前的uri
        for(TPermission permission:permissions) {
            if(permission.getUrl() != null || "".equals(permission.getUrl())) {
                String url = permission.getUrl();
                if("/main.html".equals(url)){
                    return  uri.matches(url);
                }else{
                    String regx = "(" + path + (String) url.subSequence(0, (url.lastIndexOf('/') +1)) + "){1}(.)*";
                    if(uri.matches(regx)){
                        return true;
                    }
                }
            }
        }
        httpServletResponse.sendRedirect(path + "/main.html");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
