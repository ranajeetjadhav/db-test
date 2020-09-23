package com.tradestore.app.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableAutoConfiguration
@ComponentScan({"com.tradestore.app.controller","com.tradestore.app.service","com.tradestore.app.dao"})
@PropertySource("classpath:application-test.properties")
public class ServiceBeanConfiguration {

}
