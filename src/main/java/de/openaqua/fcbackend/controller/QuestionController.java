package de.openaqua.fcbackend.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.openaqua.fcbackend.entities.ImportQuestion;
import de.openaqua.fcbackend.entities.ImportQuizz;
import de.openaqua.fcbackend.repositories.ImportQuestionsRepository;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
@RequestMapping(path = "/questions")
public class QuestionController {
  @Autowired
  private ImportQuestionsRepository repository;

  @GetMapping()
  public Optional<ImportQuizz> index() {
    log.info("GET /");
    return repository.getAll();

  }

  @GetMapping("/question={q}")
  public ResponseEntity<ImportQuestion> byId(@PathVariable final String q) {
    log.info("GET /question={}", q);
    Optional<ImportQuestion> out = repository.findByQuestions(q);
    if (!out.isPresent()) {
      throw new NoSuchQuestionException("no resource found for question" + q);
    }
    log.debug("result: {}", out.get());
    return ResponseEntity.ok(out.get());
  }

  @GetMapping("/random")
  public ResponseEntity<ImportQuestion> getRandom() {
    log.info("GET /getRandom");
    Optional<ImportQuestion> out = repository.findRandomQuestion();
    if (!out.isPresent()) {
      throw new NoSuchQuestionException("no resource found");
    }
    log.debug("result: {}", out.get());
    return ResponseEntity.ok(out.get());
  }

}
