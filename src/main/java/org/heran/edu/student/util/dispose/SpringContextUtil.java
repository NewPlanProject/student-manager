package org.heran.edu.student.util.dispose;
import org.springframework.context.ApplicationContext;

/**
 * spring上下文获取工具类
 *
 * @author guyj3@citic.com
 * @create 2017-04-13 10:14
 **/
public class SpringContextUtil {
    private static ApplicationContext applicationContext;

    public static void setApplicationContext(ApplicationContext context) {
        applicationContext = context;
    }

    public static Object getBean(String beanId) {
        return applicationContext.getBean(beanId);
    }

    public static Object getBean(String beanId, Object... args) {
        return applicationContext.getBean(beanId, args);
    }
}
