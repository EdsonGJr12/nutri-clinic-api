package br.com.nutriclinic.api.exceptionhandler;

import java.util.List;

import org.springframework.http.ProblemDetail;

public class NutriClinicProblemDetails extends ProblemDetail {
	private List<ProblemObject> problemObjects;

	public List<ProblemObject> getProblemObjects() {
		return problemObjects;
	}

	public void setProblemObjects(List<ProblemObject> problemObjects) {
		this.problemObjects = problemObjects;
	}

}
