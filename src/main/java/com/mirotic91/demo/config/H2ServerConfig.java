package com.mirotic91.demo.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.h2.tools.Server;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.sql.SQLException;

@Configuration
@Profile("local")
public class H2ServerConfig {

    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSource dataSource() throws SQLException {
        Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092").start();
        return new DataSource();
    }

}