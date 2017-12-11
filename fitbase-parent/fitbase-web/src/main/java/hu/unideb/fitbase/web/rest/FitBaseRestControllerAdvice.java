package hu.unideb.fitbase.web.rest;

import hu.unideb.fitbase.commons.pojo.exceptions.BaseException;
import hu.unideb.fitbase.commons.pojo.exceptions.ViolationException;
import hu.unideb.fitbase.commons.pojo.validator.Violation;
import hu.unideb.fitbase.commons.pojo.validator.ViolationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.util.Arrays;

@RestControllerAdvice
public class FitBaseRestControllerAdvice {

    @ExceptionHandler(ViolationException.class)
    public ResponseEntity<ViolationResponse> handleValidationException(ViolationException exception) {
        return ResponseEntity.badRequest()
                .body(ViolationResponse.builder()
                        .violationList(exception.getViolationList())
                        .build());
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ViolationResponse> handleBaseException(BaseException exception) throws ViolationException {
        if (exception instanceof ViolationException) {
            throw (ViolationException) exception;
        }

        Violation violation = Violation.builder().field("base").validationMessage(exception.getMessage()).build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ViolationResponse.builder()
                        .violationList(Arrays.asList(violation))
                        .build());
    }
}
