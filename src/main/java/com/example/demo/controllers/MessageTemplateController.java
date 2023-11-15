package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.services.MessageTemplateService;

@RestController
@RequestMapping("/api/message-template")
public class MessageTemplateController {

	private final MessageTemplateService messageTemplateService;

	@Autowired
	public MessageTemplateController(MessageTemplateService messageTemplateService) {
		this.messageTemplateService = messageTemplateService;
	}

	@GetMapping()
	public ResponseEntity<Object> getById(@RequestParam("id") String id) {
		return ResponseEntity.ok(messageTemplateService.getById(id));
	}

	@GetMapping("/name/{name}")
	public ResponseEntity<Object> getByName(@PathVariable("name") String name) {
		return ResponseEntity.ok(messageTemplateService.getByName(name));
	}
}
