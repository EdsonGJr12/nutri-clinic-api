package br.com.nutriclinic.api.exceptionhandler;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.nutriclinic.domain.exception.NegocioException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	private static final String MSG_ERRO_GENERICA_INTERNAL_SERVER = "Ocorreu um erro interno no sistema";
	private static final String MSG_ERRO_GENERICA_BAD_REQUEST = "Não foi possível realizar a operacao";
	
	@Autowired
	private MessageSource messageSource;
	
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
		
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, "Usuário ou senha inválidos");
		
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
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		return handleValidationInternal(ex, ex.getBindingResult(), headers, status, request);
	}
	
	private ResponseEntity<Object> handleValidationInternal(Exception ex, BindingResult bindingResult, HttpHeaders headers,
			HttpStatusCode status, WebRequest request) {
		        
		    String detail = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.";
		    
		    List<ProblemObject> problemObjects = bindingResult.getAllErrors().stream()
		            .map(objectError -> {
		                String message = messageSource.getMessage(objectError, new Locale("pt", "BR"));
		                
		                String name = objectError.getObjectName();
		                
		                if (objectError instanceof FieldError) {
		                    name = ((FieldError) objectError).getField();
		                }
		                
		                ProblemObject object = new ProblemObject();
		                object.setName(name);
		                object.setUserMessage(message);
		                
		                return object;
		            })
		            .collect(Collectors.toList());
		    
		    NutriClinicProblemDetails problem = new NutriClinicProblemDetails();
		    problem.setDetail(detail);
		    problem.setProblemObjects(problemObjects);
		    problem.setStatus(HttpStatus.BAD_REQUEST);
		    
		    return handleExceptionInternal(ex, problem, headers, status, request);
		}
	
}