package com.github.lucasvc.provideruntimebeans;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.jms.ConnectionFactory;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

import com.github.lucasvc.extconfiguration.Routers;

@Configuration
public class JmsConfig {

    private final ConfigurableBeanFactory beanFactory;
    private final Routers routers;

    public JmsConfig(final ConfigurableBeanFactory beanFactory, final Routers routers) {
	this.beanFactory = beanFactory;
	this.routers = routers;
    }

//    @PostConstruct
    public void registerBeans() {
	routers.getKeys().forEach(key -> {
	    beanFactory.registerSingleton(key, routers.getConnectionFactory(key));
	});
    }

//    @Bean
    public Collection<ConnectionFactory> connectionFactories() {
	return routers.getKeys().stream().map(routers::getConnectionFactory).collect(Collectors.toSet());
    }

    @Bean
    public List<DefaultJmsListenerContainerFactory> listenerContainerFactories(
	    final ObjectProvider<ConnectionFactory> connections) {
	final List<DefaultJmsListenerContainerFactory> containers = new LinkedList<>();
	for (final ConnectionFactory factory : connections) {
	    final DefaultJmsListenerContainerFactory listener = new DefaultJmsListenerContainerFactory();
	    listener.setConnectionFactory(factory);
	    containers.add(listener);
	}
	return containers;
    }

}
