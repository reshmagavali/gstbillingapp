package com.fullstack.validation;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomeValidation extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
      Map<String,String> errors =new LinkedHashMap<>();
ex.getBindingResult().getAllErrors().forEach(
        objectError -> {
            String field=((FieldError)objectError).getField();
            String msg=objectError.getDefaultMessage();
            errors.put(field,msg);
        }
);
        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }
}
