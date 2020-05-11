package de.openaqua.fcbackend.entities;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisHash;

import de.openaqua.fcbackend.SerialGenerator;

@RedisHash("Question")
public class Question {
	private String id;
	private Map<String, Boolean> answers;
	private String description;
	private String questionStr;

	@Autowired
	SerialGenerator gen;

	public Question(Map<String, Boolean> answers, String description, String question) {
		super();
		this.id = gen.getNext();
		this.answers = answers;
		this.description = description;
		this.questionStr = question;
	}

	public Question(String id, Map<String, Boolean> answers, String description, String question) {
		super();
		this.id = id;
		this.answers = answers;
		this.description = description;
		this.questionStr = question;
	}

	public Question() {
		super();
		this.id = "";
		this.description = "";
		this.answers = new HashMap<>();
		this.questionStr = "";

	}

	public void add(Boolean value, String key) {
		this.answers.put(key, value);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getQuestionStr() {
		return questionStr;
	}

	public void setQuestionStr(String questionStr) {
		this.questionStr = questionStr;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", answers=" + answers + ", description=" + description + ", questionStr="
				+ questionStr + "]";
	}

}
