package com.doku.restapi.exception;

import com.doku.restapi.model.ErrorMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.logging.Logger;

@ControllerAdvice
public class AppExceptionHandler  {

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleUserNotFoundException(DataNotFoundException ex) {
        Logger.getLogger(ex.getMessage());

        return ResponseEntity.badRequest()
                .body(ErrorMessage.builder()
                        .message("The data is invalid / not match")
                        .errors(new String[]{ex.getMessage()})
                        .build()
                );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Logger.getLogger(ex.getMessage());

        String[] errors = ex.getBindingResult().getFieldErrors().stream()
                .map(f -> f.getField() + " " + f.getDefaultMessage())
                .toArray(String[]::new);

        return ResponseEntity.badRequest()
                .body(ErrorMessage.builder()
                        .message("Arguments not valid / match")
                        .errors(errors)
                        .build()
                );
    }
}
