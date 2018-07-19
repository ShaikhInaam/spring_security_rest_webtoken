package com.springsecurity.springsecurity.customexceptions;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import com.springsecurity.springsecurity.pojo.Error;



@ControllerAdvice
@RequestMapping(produces = "application/json")
public class GlobalExceptionHandler {

	private static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<Error> handle(ApplicationException ex){
        logger.info("Global Controller:  Application Exception occurred: trace()");
        Error error = new Error(ex.getResponseStatusCode().getCode(), ex.getResponseStatusCode().getDescription());
        return new ResponseEntity<Error>(error, HttpStatus.OK);
    }
    
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Error> handleViolations(MethodArgumentNotValidException ex){
        logger.trace("Global Controller: Method Argument Not Valid Exception occurred: trace()");
        Error error = new Error(123, ex.getBindingResult().getFieldError().getDefaultMessage());
        return new ResponseEntity<Error>(error, HttpStatus.OK);
    }
    
    	
    @ExceptionHandler(HttpMessageNotReadableException.class)	 
    public ResponseEntity<Error> handleBadRequests(HttpMessageNotReadableException ex){
       
        Error error = new Error(1, "Bad Request");
        return new ResponseEntity<Error>(error, HttpStatus.OK);
    }
}
