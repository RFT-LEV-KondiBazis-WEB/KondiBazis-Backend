package hu.unideb.fitbase.service.api.rules.usermodification.password;

import hu.unideb.fitbase.commons.pojo.validator.Violation;
import hu.unideb.fitbase.service.api.domain.UserModification;
import hu.unideb.fitbase.service.api.validator.rule.Rule;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static hu.unideb.fitbase.commons.constants.rules.registration.password.PasswordRuleConstants.FIELD;
import static hu.unideb.fitbase.commons.constants.rules.registration.password.PasswordRuleConstants.UPPER_CASE_RULE;

/**
 * Validates password contains at least one upper case character.
 */
@Component
public class UserModificationPasswordShouldContainOneUpperCaseCharacter implements Rule<UserModification> {

    protected static final String MATCHER = ".*[A-Z].*";

    @Override
    public List<Violation> validate(UserModification userModification) {
        if (userModification.getPassword() == null) {
            return Collections.<Violation>emptyList();
        }
        return userModification.getPassword() != null && userModification.getPassword().matches(MATCHER)
                ? Collections.<Violation>emptyList()
                : Arrays.asList(Violation.builder()
                .field(FIELD)
                .validationMessage(UPPER_CASE_RULE)
                .build());
    }
}
