package com.pl.premiere_zone.common.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    //400: Validation
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiExceptionHandler> handleValidation(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        Map<String, String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        FieldError::getDefaultMessage,
                        (m1, m2) -> m1));

        return ResponseEntity.badRequest().body(
                buildBody(ex, request, HttpStatus.BAD_REQUEST, errors));
    }

    // 409: DB constraints
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiExceptionHandler> handleConstraint(
            DataIntegrityViolationException ex,
            HttpServletRequest request) {

        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                buildBody(ex, request, HttpStatus.CONFLICT, null));
    }

    // 404: Domain not‑found
    @ExceptionHandler(PlayerNotFoundException.class)
    public ResponseEntity<ApiExceptionHandler> handleNotFound(
            PlayerNotFoundException ex,
            HttpServletRequest request) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                buildBody(ex, request, HttpStatus.NOT_FOUND, null));
    }

    // 500: Catch‑all
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiExceptionHandler> handleGeneric(
            Exception ex, HttpServletRequest request) {

        log.error("Unhandled exception", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                buildBody(ex, request, HttpStatus.INTERNAL_SERVER_ERROR, null));
    }


    private ApiExceptionHandler buildBody(Exception ex,
                                          HttpServletRequest req,
                                          HttpStatus status,
                                          Map<String, String> validation) {

        return ApiExceptionHandler.builder()
                .timestamp(Instant.now())
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(ex.getMessage())
                .path(req.getRequestURI())
                .validationErrors(validation)
                .build();
    }
}
