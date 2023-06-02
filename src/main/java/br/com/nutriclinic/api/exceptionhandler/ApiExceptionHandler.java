package br.com.nutriclinic.api.exceptionhandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.nutriclinic.domain.exception.NegocioException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	private static final String MSG_ERRO_GENERICA_INTERNAL_SERVER = "Ocorreu um erro interno no sistema";
	private static final String MSG_ERRO_GENERICA_BAD_REQUEST = "Não foi possível realizar a operacao";
	
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<?> handleNegocioException(NegocioException e,
			WebRequest request) {
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, e.getMessage());
		problemDetail.setTitle(MSG_ERRO_GENERICA_BAD_REQUEST);
		
		return handleExceptionInternal(e, problemDetail, 
				new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<?> handleBadCredentials(BadCredentialsException e,
			WebRequest request) {
		
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, e.getMessage());
		
		return handleExceptionInternal(e, problemDetail, 
				new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleOutrasExceptions(Exception e,
			WebRequest request) {
		
		e.printStackTrace();
		
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, MSG_ERRO_GENERICA_INTERNAL_SERVER);
		
		return handleExceptionInternal(e, problemDetail, 
				new HttpHeaders(), status, request);
	}
}