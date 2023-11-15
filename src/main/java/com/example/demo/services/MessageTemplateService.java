package com.example.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.commons.exceptions.NotFoundException;
import com.example.demo.entities.MessageTemplate;
import com.example.demo.repositories.MessageTemplateRepository;

@Service
public class MessageTemplateService {

	private final MessageTemplateRepository messageTemplateRepository;

	@Autowired
	public MessageTemplateService(MessageTemplateRepository messageTemplateRepository) {
		this.messageTemplateRepository = messageTemplateRepository;
	}

	public MessageTemplate getById(String id) {
		Optional<MessageTemplate> optMsgTmplt = messageTemplateRepository.findById(id);
		return optMsgTmplt.orElseThrow(() -> new NotFoundException("message template with id: "+id+" not found"));
	}

	public MessageTemplate getByName(String name) {
		Optional<MessageTemplate> optMsgTmplt = messageTemplateRepository.findByName(name);
		return optMsgTmplt.orElseThrow(() -> new NotFoundException("message template with name: "+name+" not found"));
	}
}
