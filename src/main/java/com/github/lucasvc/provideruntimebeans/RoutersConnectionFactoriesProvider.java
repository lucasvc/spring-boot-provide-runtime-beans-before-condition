package com.github.lucasvc.provideruntimebeans;

import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;

import com.github.lucasvc.extconfiguration.Routers;

public class RoutersConnectionFactoriesProvider
	implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(final ConfigurableApplicationContext applicationContext) {
	applicationContext.addBeanFactoryPostProcessor(new PostProcessor());
    }

    static class PostProcessor implements BeanDefinitionRegistryPostProcessor, Ordered {
	@Override
	public void postProcessBeanDefinitionRegistry(final BeanDefinitionRegistry registry) throws BeansException {
	    final Map<String, BeanDefinition> availableRouters = Stream.of(registry.getBeanDefinitionNames())
		    .map(name -> new AbstractMap.SimpleEntry<>(name, registry.getBeanDefinition(name)))
		    .filter(entry -> entry.getValue().getBeanClassName().equals(Routers.class.getName()))
		    .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));
	    if (availableRouters.isEmpty()) {
		throw new NoSuchBeanDefinitionException(Routers.class);
	    }
	}

	@Override
	public void postProcessBeanFactory(final ConfigurableListableBeanFactory beanFactory) throws BeansException {
	    // remove
	}

	@Override
	public int getOrder() {
	    return Ordered.LOWEST_PRECEDENCE;
	}
    }
}
