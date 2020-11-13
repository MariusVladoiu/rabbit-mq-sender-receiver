package com.example.workmanager.controller;

import com.example.workmanager.model.ToBeCheckSwitch;
import com.example.workmanager.service.RabbitMQSender;
import com.example.workmanager.service.RailSwitchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/rabbitmq")
public class RabbitMQWebController {

	@Autowired
	RabbitMQSender rabbitMQSender;
	@Autowired
	RailSwitchService railSwitchService;

	@GetMapping(value = "/partitionate")
	public String partitionateCheckingList() {

		List<ToBeCheckSwitch> lst = railSwitchService.partitionateCheckingListInSixChunks();
		rabbitMQSender.send(lst);

		return "Listele de macazuri ce trebuiesc verificate au fost trimise cu succes. Veti vedea in tabel lista de macazuri re-initializata";
	}

}

