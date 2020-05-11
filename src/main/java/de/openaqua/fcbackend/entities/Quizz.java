package de.openaqua.fcbackend.entities;

import java.util.HashMap;
import java.util.Map;

public class Quizz {
	private String description;
	private Map<String, Question> questions;

	public Question get(String key) {
		return questions.get(key);
	}

	public Question add(String key, Question value) {
		return questions.put(key, value);
	}

	public Quizz(String description, Map<String, Question> questions) {
		super();
		this.description = description;
		this.questions = questions;
	}

	public Quizz() {
		super();
		this.description = "";
		this.questions = new HashMap<String, Question>();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Map<String, Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Map<String, Question> questions) {
		this.questions = questions;
	}

	@Override
	public String toString() {
		return "Quizz [description=" + description + ", questions=" + questions + "]";
	}

}
