package hu.unideb.fitbase.web.rest;

import hu.unideb.fitbase.commons.pojo.exceptions.ViolationException;
import hu.unideb.fitbase.commons.pojo.validator.ViolationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class FitBaseControllerAdvice {

    @ExceptionHandler(ViolationException.class)
    public ResponseEntity<ViolationResponse> handleValidationException(ViolationException exception) {
        return ResponseEntity.badRequest()
                .body(ViolationResponse.builder()
                        .violationList(exception.getViolationList())
                        .build());
    }

}
