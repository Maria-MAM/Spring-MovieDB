package com.movieDB.movieDB.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { EntryNotFoundException.class })
    protected ResponseEntity<Object> handleConflict(Exception ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();

        if (ex instanceof MovieNotFoundException) {
            String bodyOfResponse = "This movie was not found!";
            HttpStatus status = HttpStatus.NOT_FOUND;
            MovieNotFoundException mnfe = (MovieNotFoundException) ex;
            return handleEntryNotFoundException(mnfe, bodyOfResponse, headers, status, request);
        } else if (ex instanceof GenreNotFoundException) {
            String bodyOfResponse = "This genre was not found!";
            HttpStatus status = HttpStatus.NOT_FOUND;
            GenreNotFoundException gnfe = (GenreNotFoundException) ex;
            return handleEntryNotFoundException(gnfe, bodyOfResponse, headers, status, request);
        } else {
            String bodyOfResponse = "Something went wrong! Please try again!";
            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            return handleExceptionInternal(ex, bodyOfResponse, headers, status, request);
        }

    }

    protected ResponseEntity<Object> handleEntryNotFoundException(EntryNotFoundException ex, String body, HttpHeaders headers,
                                                                 HttpStatus status, WebRequest request) {
        return handleExceptionInternal(ex, body, headers, status, request);
    }

    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, String body, HttpHeaders headers,
                                                               HttpStatus status, WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }
        return new ResponseEntity<>(body, headers, status);
    }
}


