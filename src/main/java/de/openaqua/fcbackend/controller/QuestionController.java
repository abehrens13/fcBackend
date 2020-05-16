package de.openaqua.fcbackend.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.openaqua.fcbackend.entities.Question;
import de.openaqua.fcbackend.entities.Quizz;
import de.openaqua.fcbackend.repositories.QuestionsRepository;

@RestController
@RequestMapping(path = "/questions")
public class QuestionController {
  private static final Logger LOG = LoggerFactory.getLogger(QuestionController.class);

  @Autowired
  private QuestionsRepository repository;

  @GetMapping()
  public Optional<Quizz> index() {
    LOG.info("GET /");
    return repository.getAll();

  }

  @GetMapping("/question={q}")
  public ResponseEntity<Question> byId(@PathVariable final String q) {
    LOG.info("GET /question={}", q);
    Optional<Question> out = repository.findByQuestions(q);
    if (!out.isPresent()) {
      throw new NoSuchQuestionException("no resource found for question" + q);
    }
    return ResponseEntity.ok(out.get());
  }

  @GetMapping("/random")
  public ResponseEntity<Question> getRandom() {
    LOG.info("GET /getRandom");
    Optional<Question> out = repository.findRandomQuestion();
    if (!out.isPresent()) {
      throw new NoSuchQuestionException("no resource found");
    }
    return ResponseEntity.ok(out.get());
  }

}
