package com.rvalencia.dojooverflow.repositories;

import org.springframework.data.repository.CrudRepository;

import com.rvalencia.dojooverflow.models.Question;

public interface QuestionRepo extends CrudRepository<Question, Long>{

}
