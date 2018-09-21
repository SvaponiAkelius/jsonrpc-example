package io.github.svaponi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JsonRpcClientConfig {

    @Bean
    public com.googlecode.jsonrpc4j.spring.JsonProxyFactoryBean jsonProxyFactoryBean(){
        com.googlecode.jsonrpc4j.spring.JsonProxyFactoryBean jsonProxyFactoryBean = new com.googlecode.jsonrpc4j.spring.JsonProxyFactoryBean();
        jsonProxyFactoryBean.setServiceUrl("http://localhost:8080/UserService.json");
        jsonProxyFactoryBean.setServiceInterface(UserService.class);
        return jsonProxyFactoryBean;
    }
}
