package com.orderService.kafka;

import java.awt.Event;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.base.dto.OrderEvent;

@Service

public class OrderProducer {

	private static final Logger logger=LoggerFactory.getLogger(OrderProducer.class);
	
			private NewTopic topic;
	
	private KafkaTemplate<String, OrderEvent> kafkaTemplate;

	public OrderProducer(NewTopic topic, KafkaTemplate<String, OrderEvent> kafkaTemplate) {
		super();
		this.topic = topic;
		this.kafkaTemplate = kafkaTemplate;
	}
	
	
	public void sendMessage(OrderEvent order) {
		
		logger.info("fjkf"+order.toString());
		 org.springframework.messaging.Message<OrderEvent> messege = MessageBuilder.withPayload(order)
				.setHeader(KafkaHeaders.TOPIC, topic.name())
				.build();
		 kafkaTemplate.send(messege);
				
	}
	
	
	
	}
