package hu.unideb.fitbase.service.api.validator;

import hu.unideb.fitbase.commons.pojo.exceptions.ViolationException;
import hu.unideb.fitbase.commons.pojo.validator.Violation;
import hu.unideb.fitbase.service.api.validator.rule.Rule;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;

/**
 * Generic validator for requests.
 *
 * @param <T>
 *            validated request type.
 */
public class AbstractValidator<T> {

    @Autowired
    private List<Rule<T>> rules;

    public AbstractValidator(List<Rule<T>> rules) {
        super();
        this.rules = rules;
    }

    /**
     * Validates request.
     *
     * @param request
     *            request to be validated.
     */
    public void validate(T request) throws ViolationException {
        List<Violation> violations = new LinkedList<>();
        for (Rule<T> rule : rules) {
            violations.addAll(rule.validate(request));
        }
        if (!violations.isEmpty()) {
            throw new ViolationException(violations);
        }
    }

}
