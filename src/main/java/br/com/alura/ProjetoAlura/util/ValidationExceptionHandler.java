package br.com.alura.ProjetoAlura.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


import jakarta.validation.ConstraintViolationException;

import java.util.List;

@ControllerAdvice
public class ValidationExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<List<ErrorItemDTO>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<ErrorItemDTO> errors = ex.getBindingResult().getFieldErrors().stream().map(ErrorItemDTO::new).toList();
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(InvalidCourseCodeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleInvalidCourseCodeException(InvalidCourseCodeException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException ex) {
        return ResponseEntity.badRequest().body("Validation error:" + ex.getMessage());
    }
}