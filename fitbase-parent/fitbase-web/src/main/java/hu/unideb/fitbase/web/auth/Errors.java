package hu.unideb.fitbase.web.auth;

import hu.unideb.fitbase.commons.pojo.validator.Violation;

import java.util.List;

public class Errors {

    private List<Violation> errors;


    public Errors(List<Violation> errors) {
        this.errors = errors;
    }

    public List<Violation> getErrors() {
        return errors;
    }
}
