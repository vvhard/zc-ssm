package zc.portal.listener;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyContextPathListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        context.setAttribute("ctx", context.getContextPath());
        // 准备数据
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
