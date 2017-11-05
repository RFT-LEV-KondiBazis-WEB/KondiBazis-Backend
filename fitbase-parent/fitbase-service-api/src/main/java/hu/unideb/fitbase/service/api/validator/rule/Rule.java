package hu.unideb.fitbase.service.api.validator.rule;

import hu.unideb.fitbase.commons.pojo.validator.Violation;

import java.util.List;

/**
 * Rule created for Validators.
 */
public interface Rule<T> {

    /**
     * Validates request.
     *
     * @param request request to be validated.
     * @return violation list if the request does not fit the rule.
     */
    List<Violation> validate(T request);

}
