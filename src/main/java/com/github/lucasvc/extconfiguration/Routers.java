package com.github.lucasvc.extconfiguration;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.jms.ConnectionFactory;

import org.springframework.jms.connection.SingleConnectionFactory;
import org.springframework.stereotype.Component;

@Component
public class Routers {

    public Set<String> getKeys() {
	return Stream.<String>of(
		// incoming keys from configuration
		"fixed").collect(Collectors.toSet());
    }

    public ConnectionFactory getConnectionFactory(final String routerKey) {
	// provided by other means, set as example
	return new SingleConnectionFactory();
    }

}
