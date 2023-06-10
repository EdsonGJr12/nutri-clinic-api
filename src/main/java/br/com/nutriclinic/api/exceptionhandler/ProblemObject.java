package br.com.nutriclinic.api.exceptionhandler;

public class ProblemObject {

	private String name;

	private String userMessage;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserMessage() {
		return userMessage;
	}

	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}

}