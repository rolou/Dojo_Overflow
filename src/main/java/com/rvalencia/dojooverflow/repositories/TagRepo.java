package com.rvalencia.dojooverflow.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import jakarta.servlet.jsp.tagext.Tag;

public interface TagRepo extends CrudRepository<Tag, Long> {
	Optional<Tag> findBySubject(String subject);
}
