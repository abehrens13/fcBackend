package de.openaqua.fcbackend.repositories;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.util.Map;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import de.openaqua.fcbackend.entities.ImportQuestion;
import de.openaqua.fcbackend.entities.ImportQuizz;

@Repository
public class QuestionsRepository {
  private static final Logger LOG = LoggerFactory.getLogger(QuestionsRepository.class);
  private static final SecureRandom secureRandom = new SecureRandom();
  private ImportQuizz quizz;

  @Value("classpath:/static/questions2.yaml")
  private Resource questionFile;

  public QuestionsRepository() {
    LOG.debug("setup QuestionsRepository()");
    quizz = null;
  }

  public void loadDefaultQuestionFile() {
    LOG.info("loadDefaultQuestionFile");
    ObjectMapper om = new ObjectMapper(new YAMLFactory());
    try {
      InputStream dbAsStream = questionFile.getInputStream();
      quizz = om.readValue(dbAsStream, ImportQuizz.class);

      LOG.info("Loaded quizz data from {}", questionFile.getFilename());

      Map<String, ImportQuestion> m = quizz.getQuestions();
      m.forEach((key, value) -> value.setQuestionStr(key));

    } catch (IOException e1) {
      LOG.error("cannot load from file {} cause {}", questionFile.getFilename(), e1.getLocalizedMessage());
    }
  }

  public void saveNew(String filename, ImportQuizz quizz) {
    LOG.debug("saveNew({})", filename);
    ObjectMapper om = new ObjectMapper(new YAMLFactory());
    try {
      om.writeValue(new File(filename), quizz);
    } catch (IOException e) {
      LOG.error("cannot write to file {} cause {}", filename, e.getLocalizedMessage());
    }
  }

  public Optional<ImportQuizz> getAll() {
    LOG.debug("getAll()");

    if (quizz == null) {
      loadDefaultQuestionFile();
    }
    if (quizz == null) {
      return Optional.empty();
    } else {
      return Optional.of(quizz);
    }

  }

  public int getAmountOfQuestions() {
    LOG.debug("getAmountOfQuestions()");
    if (quizz == null) {
      loadDefaultQuestionFile();
    }
    if (quizz == null) {
      return 0;
    } else {
      return quizz.getQuestions().size();
    }
  }

  public Optional<ImportQuestion> findByQuestions(String question) {
    LOG.debug("findByQuestions({})", question);
    if (quizz == null) {
      loadDefaultQuestionFile();
    }
    if (quizz != null) {
      ImportQuestion q = quizz.get(question);
      if (q != null) {
        return Optional.of(q);
      }

    }
    return Optional.empty();
  }

  private int getRandomQuestionId() {
    LOG.debug("getRandomQuestionId()");
    int max = getAmountOfQuestions() - 1;
    return secureRandom.nextInt(max);
  }

  public Optional<ImportQuestion> findRandomQuestion() {
    LOG.debug("findRandomQuestion()");
    if (quizz == null) {
      loadDefaultQuestionFile();
    }
    if (quizz != null) {
      Object[] keys = quizz.getQuestions().keySet().toArray();
      String question = (String) keys[getRandomQuestionId()];
      return findByQuestions(question);
    }
    return Optional.empty();
  }
}
