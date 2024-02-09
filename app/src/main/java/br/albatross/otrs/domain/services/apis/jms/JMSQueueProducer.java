package br.albatross.otrs.domain.services.apis.jms;

import java.io.Serializable;

public interface JMSQueueProducer<T extends Serializable> {

	void sendToJMSQueue(T t);
	
}
