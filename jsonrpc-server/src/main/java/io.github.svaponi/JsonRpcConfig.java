package io.github.svaponi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JsonRpcConfig {

    @Bean
    public org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping beanNameUrlHandlerMapping() {
        return new org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping();
    }

    @Bean
    public UserService userService() {
        return new UserServiceImpl();
    }

    @Bean(name = "/UserService.json")
    public com.googlecode.jsonrpc4j.spring.JsonServiceExporter jsonServiceExporter() {
        com.googlecode.jsonrpc4j.spring.JsonServiceExporter exporter = new com.googlecode.jsonrpc4j.spring.JsonServiceExporter();
        exporter.setService(userService());
        exporter.setServiceInterface(UserService.class);
        return exporter;
    }
}
