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
	private Logger log = LoggerFactory.getLogger(QuestionController.class);

	@Autowired
	QuestionsRepository repository;

	protected void makeSampleFile() {

		Quizz quizz = new Quizz();
		Question q = new Question();
		q.add(true, "Andrey");
		q.add(false, "Otto");
		q.add(false, "Egon");
		q.setDescription("Beschreibung");
		quizz.add("Wer bin ich", q);
		Question q2 = new Question();
		q2.add(true, "Andrey 2");
		q2.add(false, "Otto2");
		q2.add(false, "Egon2");
		q2.setDescription("Beschreibung 2");
		quizz.add("Wer bin ich2", q2);
		repository.saveNew("/tmp/liste.yml", quizz);

	}

	@GetMapping()
	public Optional<Quizz> index() {
		log.info("GET /");
		return repository.getAll();

	}

	@GetMapping("/question={q}")
	public ResponseEntity<Question> byId(@PathVariable final String q) {
		log.info("GET /question={}", q);
		Optional<Question> out = repository.findByQuestions(q);
		if (!out.isPresent()) {
			throw new NoSuchQuestionException("no resource found for question" + q);
		}
		return ResponseEntity.ok(out.get());
	}

	@GetMapping("/random")
	public ResponseEntity<Question> getRandom() {
		log.info("GET /getRandom");
		Optional<Question> out = repository.findRandomQuestion();
		if (!out.isPresent()) {
			throw new NoSuchQuestionException("no resource found");
		}
		return ResponseEntity.ok(out.get());
	}

}