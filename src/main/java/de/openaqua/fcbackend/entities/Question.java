package de.openaqua.fcbackend.entities;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.redis.core.RedisHash;

@RedisHash("Question")
public class Question {
	private Map<String, Boolean> answers;
	private String description;
	private String question;

	public Question(Map<String, Boolean> answers, String description, String question) {
		super();
		this.answers = answers;
		this.description = description;
		this.question = question;
	}

	public Question() {
		super();
		this.description = "";
		this.answers = new HashMap<>();
		this.question = "";

	}

	public Map<String, Boolean> getAnswers() {
		return answers;
	}

	public void setAnswers(Map<String, Boolean> answers) {
		this.answers = answers;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void add(Boolean value, String key) {
		answers.putIfAbsent(key, value);
	}

	public Boolean get(String key) {
		return answers.get(key);
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	@Override
	public String toString() {
		return "Question [answers=" + answers + ", description=" + description + ", question=" + question + "]";
	}
}
