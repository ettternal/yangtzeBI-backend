package com.kingyqh.yangtzebi.config;

import lombok.Data;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//初始化RedissonClient实例
@Data
@ConfigurationProperties(prefix = "spring.redis")//读取yml中的哪个配置
@Configuration
public class RedissonConfig {
    private Integer database;
    private String host;
    private Integer port;
    private String password;

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer()//配置Redis单机服务器
                .setDatabase(database)//设置Redis服务器地址
                //.setAddress("redis://127.0.0.1:6379");
                .setAddress("redis://" + host + ":" + port);
        RedissonClient redisson = Redisson.create();
        return redisson;
    }

}
