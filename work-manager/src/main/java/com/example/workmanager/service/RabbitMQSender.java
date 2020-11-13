package com.example.workmanager.service;

import com.example.workmanager.model.Employee;
import com.example.workmanager.model.ToBeCheckSwitch;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RabbitMQSender {
	
	/*@Autowired
	private AmqpTemplate amqpTemplate;*/

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Value("${railswitch.rabbitmq.exchange}")
	private String exchange;
	
	@Value("${railswitch.rabbitmq.routingkey}")
	private String routingkey;


	public void send2(Employee company) {
		//rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
		rabbitTemplate.convertAndSend(exchange, routingkey, company);
		System.out.println("Send msg = " + company);
	}
	public void send(List<ToBeCheckSwitch> lst) {
		//rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
		rabbitTemplate.convertAndSend(exchange, routingkey, lst);
		System.out.println("Send msg = " + lst);
	}
}