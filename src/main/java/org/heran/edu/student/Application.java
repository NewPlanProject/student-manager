package org.heran.edu.student;

import org.heran.edu.student.util.dispose.SpringContextUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Hello world!
 *
 */

@SpringBootApplication(scanBasePackages = {"org.heran.edu.student",
        "org.heran.edu.student.util.session"})
@MapperScan("org.heran.edu.student.dao")
@ServletComponentScan(basePackages={"org.heran.edu.student.druid"})
@EnableTransactionManagement
public class Application
{
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
        String[] activeProfiles = ctx.getEnvironment().getActiveProfiles();
        for (String profile : activeProfiles) {
            System.out.println("Spring boot 运行的环境为:" + profile);
        }
    }
}
