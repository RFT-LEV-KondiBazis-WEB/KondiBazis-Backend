package hu.unideb.fitbase.service.api.rules.registration.password;

import hu.unideb.fitbase.commons.pojo.request.RegistrationRequest;
import hu.unideb.fitbase.commons.pojo.validator.Violation;
import hu.unideb.fitbase.service.api.validator.rule.Rule;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static hu.unideb.fitbase.commons.constants.rules.registration.password.PasswordRuleConstants.DIGIT_RULE;
import static hu.unideb.fitbase.commons.constants.rules.registration.password.PasswordRuleConstants.FIELD;

/**
 * Validates password contains at least one digit.
 */
@Component
public class PasswordShouldContainDigit implements Rule<RegistrationRequest> {

    protected static final String MATCHER = ".*[0-9].*";

    @Override
    public List<Violation> validate(RegistrationRequest request) {
        return request.getPassword() != null && request.getPasswordConfirm().matches(MATCHER)
                ? Collections.<Violation>emptyList()
                : Arrays.asList(Violation.builder()
                .field(FIELD)
                .validationMessage(DIGIT_RULE)
                .build());
    }
}
