package com.github.lucasvc.provideruntimebeans;

import javax.jms.Message;

import org.springframework.jms.core.JmsTemplate;

public class Receiver {
    private final JmsTemplate template;

    public Receiver(final JmsTemplate template) {
	this.template = template;
	this.template.setReceiveTimeout(JmsTemplate.RECEIVE_TIMEOUT_INDEFINITE_WAIT);
    }

    public void listen() {
	do {
	    final Message message = template.receive();
	    System.out.println("received <" + message + ">");
	} while (true);
    }

}
