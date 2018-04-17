package org.heran.edu.student.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 配置允许跨域访问,这样做不安全。生产环境替换为nginx反向代理解决跨域问题的形式
 */
@Configuration
public class CorsConfig {
    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*"); // 1
        corsConfiguration.addAllowedHeader("*"); // 2
        corsConfiguration.addAllowedMethod("*"); // 允许所有方法包括"GET", "POST", "DELETE", "PUT"等等

        //corsConfiguration.setAllowCredentials(true);
        //使用setAllowCredentials的方式解决跨域问题只支持ie10以上。
        //如果使用默认的配合CookierHttpSessionStrategy的session方式
        // 前后端一起开启，开启之后就能够读写浏览器的Cookies。该字段可选。它的值是一个布尔值，表示是否允许发送Cookie。
        // 默认情况下，Cookie不包括在CORS请求之中。设为true，即表示服务器明确许可，
        // Cookie可以包含在请求中，一起发给服务器。这个值也只能设为true，如果服务器不要浏览器发送Cookie，删除该字段即可。

        //corsConfiguration.addExposedHeader("x-auth-token");
        //配合HeaderHttpSessionStrategy的session方式。token的方式解决跨域问题。
        // CORS请求时。XMLHttpRequest对象的getResponseHeader()方法只能拿到6个基本字段：Cache-Control、Content-Language、Content-Type、Expires、Last-Modified、Pragma。如果想拿到其他字段，
        // 就必须在Access-Control-Expose-Headers里面指定。上面的例子指定，getResponseHeader(‘FooBar’)可以返回FooBar字段的值。
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig()); // 4
        return new CorsFilter(source);
    }
}
