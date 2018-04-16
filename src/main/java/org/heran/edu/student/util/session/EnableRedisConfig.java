package org.heran.edu.student.util.session;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@Slf4j
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 1800)
public class EnableRedisConfig {

    @Value("${spring.redis.database:0}")
    private int database;

    @Value("${spring.redis.host:10.76.3.66}")
    private String host;

    @Value("${spring.redis.port:6379}")
    private int port;

    @Value("${spring.redis.password:redis123}")
    private String password;

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setDatabase(this.database);
        jedisConnectionFactory.setHostName(this.host);
        jedisConnectionFactory.setPort(this.port);
        jedisConnectionFactory.setPassword(this.password);
        log.info("Init redis by data={}, host={}, port={}, password={}", this.database, this.host, this.port, this.password);
        return jedisConnectionFactory;
    }
}