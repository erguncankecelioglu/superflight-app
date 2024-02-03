package com.superflight1.util.exception;

import com.superflight1.util.exception.customExceptions.BadRequestException;
import com.superflight1.util.exception.customExceptions.NotAuthorizedException;
import com.superflight1.util.exception.customExceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.LocalDateTime;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Object> handleNotFound(NotFoundException e, WebRequest request) {
        return new ResponseEntity<>(new ErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleBadRequest(BadRequestException e, WebRequest request) {
        return new ResponseEntity<>(new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleNotAuthorized(NotAuthorizedException e, WebRequest request) {
        return new ResponseEntity<>(new ErrorResponse(e.getMessage(),HttpStatus.UNAUTHORIZED, LocalDateTime.now()), HttpStatus.UNAUTHORIZED);
    }
}
