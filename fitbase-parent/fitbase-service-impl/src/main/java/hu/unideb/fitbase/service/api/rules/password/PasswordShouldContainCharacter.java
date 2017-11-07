package hu.unideb.fitbase.service.api.rules.password;

import hu.unideb.fitbase.commons.pojo.request.RegistrationRequest;
import hu.unideb.fitbase.commons.pojo.validator.Violation;
import hu.unideb.fitbase.service.api.validator.rule.Rule;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static hu.unideb.fitbase.commons.constants.rules.registration.password.PasswordRuleConstants.CHARACTER_RULE;
import static hu.unideb.fitbase.commons.constants.rules.registration.password.PasswordRuleConstants.FIELD;


/**
 * Validates password contains at least one character.
 */
@Component
public class PasswordShouldContainCharacter implements Rule<RegistrationRequest> {

    protected static final String MATCHER = ".*[a-z].*";

    @Override
    public List<Violation> validate(RegistrationRequest request) {
        return request.getPassword() != null && request.getPasswordConfirm()
                .toLowerCase()
                .matches(MATCHER)
                ? Collections.<Violation>emptyList()
                : Arrays.asList(Violation.builder()
                .field(FIELD)
                .validationMessage(CHARACTER_RULE)
                .build());
    }
}
