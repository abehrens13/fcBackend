package de.openaqua.fcbackend.entities;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.redis.core.RedisHash;

@RedisHash("Quizz")
public class ImportQuizz {
	private String description;
	private Map<String, ImportQuestion> questions;

	public ImportQuestion get(String key) {
		return questions.get(key);
	}

	public ImportQuestion add(String key, ImportQuestion value) {
		return questions.put(key, value);
	}

	public ImportQuizz(String description, Map<String, ImportQuestion> questions) {
		super();
		this.description = description;
		this.questions = questions;
	}

	public ImportQuizz() {
		super();
		this.description = "";
		this.questions = new HashMap<>();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Map<String, ImportQuestion> getQuestions() {
		return questions;
	}

	public void setQuestions(Map<String, ImportQuestion> questions) {
		this.questions = questions;
	}

	@Override
	public String toString() {
		return "Quizz [description=" + description + ", questions=" + questions + "]";
	}

}
