package de.openaqua.fcbackend.entities;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("Question")
public class ImportQuestion {
	private Map<String, Boolean> answers;
	@Id
	private String description;
	private String questionStr;

	public ImportQuestion(Map<String, Boolean> answers, String description, String question) {
		super();
		this.answers = answers;
		this.description = description;
		this.questionStr = question;
	}

	public ImportQuestion() {
		super();
		this.description = "";
		this.answers = new HashMap<>();
		this.questionStr = "";

	}

	public void add(Boolean value, String key) {
		this.answers.put(key, value);
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
		return "Question [answers=" + answers + ", description=" + description + ", questionStr=" + questionStr + "]";
	}

}
