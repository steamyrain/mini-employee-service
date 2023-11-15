package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.MessageTemplate;

@Repository
public interface MessageTemplateRepository extends JpaRepository<MessageTemplate, String> {

	Optional<MessageTemplate> findByName(String name);
}
