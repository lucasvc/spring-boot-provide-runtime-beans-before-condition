package com.github.lucasvc.main;

import javax.jms.ConnectionFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

import com.github.lucasvc.extconfiguration.Routers;
import com.github.lucasvc.provideruntimebeans.JmsConfig;

@SpringBootApplication
@Import({ Routers.class, JmsConfig.class, })
public class ExternalApplication {

    public static void main(final String[] args) {
	final ConfigurableApplicationContext context = SpringApplication.run(ExternalApplication.class, args);
	context.getBeansOfType(ConnectionFactory.class);
    }

}
