package com.example.user.api.simpleuserservice.exceptions.errorHandlers;


import com.example.user.api.simpleuserservice.exceptions.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class EntityNotFoundHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException error) {
        return new ResponseEntity<Object>(error.getLocalizedMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
}
