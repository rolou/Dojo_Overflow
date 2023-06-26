package com.rvalencia.dojooverflow.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rvalencia.dojooverflow.models.Answer;
import com.rvalencia.dojooverflow.models.Question;
import com.rvalencia.dojooverflow.repositories.AnswerRepo;
import com.rvalencia.dojooverflow.repositories.QuestionRepo;
import com.rvalencia.dojooverflow.repositories.TagRepo;

import jakarta.servlet.jsp.tagext.Tag;

@Service
public class MainService {
	
	private final QuestionRepo questionRepo;
    private final TagRepo tagRepo;
    private final AnswerRepo answerRepo;

    @Autowired
    public MainService(QuestionRepo questionRepo,
                       TagRepo tagRepo,
                       AnswerRepo answerRepo) {
        this.questionRepo = questionRepo;
        this.tagRepo = tagRepo;
        this.answerRepo = answerRepo;
    }

    public Iterable<Question> findAllQuestions() {
        return questionRepo.findAll();
    }

    public Question findQuestionById(Long id) {
        Optional<Question> optionalQuestion = questionRepo.findById(id);
        if (optionalQuestion.isPresent()) {
            return optionalQuestion.get();
        }
        return null;
    }

    public void createQuestion(String newQuestion, String newTags) {
        Question question = new Question();
        question.setQuestion(newQuestion);

        String[] subjects = newTags.split(",");
        for (String subject : subjects) {
            String trimmedSubject = subject.trim();
            Optional<Tag> optionalTag = tagRepo.findBySubject(trimmedSubject);

            if (optionalTag.isPresent()) {
                question.addTag(optionalTag.get());
            } else {
                Tag tag = new Tag();
                tag.setSubject(trimmedSubject);
                tagRepo.save(tag);
                question.addTag(tag);
            }
        }
        questionRepo.save(question);
    }

    public void createAnswer(Long id, String newAnswer) {
        Optional<Question> optionalQuestion = questionRepo.findById(id);
        if (optionalQuestion.isPresent()) {
            Question question = optionalQuestion.get();
            Answer answer = new Answer();
            answer.setAnswer(newAnswer);
            answer.setQuestion(question);
            answerRepo.save(answer);
        }
    }
}
