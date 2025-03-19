package io.autoinvestor.ui;

import io.autoinvestor.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = {RegisterUserController.class,})
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicatedException.class)
    public ResponseEntity<ErrorResponse> handleDuplicatedException(DuplicatedException ex) {
        return ErrorResponse.builder().status(498).message(ex.getMessage()).build();
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorResponse> handleConflictException(ConflictException ex) {
        return ErrorResponse.builder().status(HttpStatus.CONFLICT).message(ex.getMessage()).build();
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException ex) {
        return ErrorResponse.builder().status(HttpStatus.BAD_REQUEST).message(ex.getMessage()).build();
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex) {
        return ErrorResponse.builder().status(HttpStatus.NOT_FOUND).message(ex.getMessage()).build();
    }

    @ExceptionHandler(InternalErrorException.class)
    public ResponseEntity<ErrorResponse> handleInternalErrorException(InternalErrorException ex) {
        return ErrorResponse.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).message(ex.getMessage()).build();
    }

    @ExceptionHandler(EmailNotValid.class)
    public ResponseEntity<ErrorResponse> handleEmailNotValid(EmailNotValid ex) {
        return ErrorResponse.builder().status(HttpStatus.valueOf(409)).message(ex.getMessage()).build();
    }
}
