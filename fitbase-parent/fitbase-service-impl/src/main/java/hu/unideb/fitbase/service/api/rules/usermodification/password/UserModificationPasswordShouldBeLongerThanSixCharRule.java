package hu.unideb.fitbase.service.api.rules.usermodification.password;

import hu.unideb.fitbase.commons.pojo.validator.Violation;
import hu.unideb.fitbase.service.api.domain.UserModification;
import hu.unideb.fitbase.service.api.validator.rule.Rule;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static hu.unideb.fitbase.commons.constants.rules.registration.password.PasswordRuleConstants.FIELD;
import static hu.unideb.fitbase.commons.constants.rules.registration.password.PasswordRuleConstants.LENGTH_RULE;

/**
 * Validates password length, that must be longer than 6 characters.
 */
@Component
public class UserModificationPasswordShouldBeLongerThanSixCharRule implements Rule<UserModification> {

    @Override
    public List<Violation> validate(UserModification userModification) {
        if (userModification.getPassword() == null) {
            return Collections.<Violation>emptyList();
        }
        return userModification.getPassword() != null &&
                userModification.getPassword().length() < 6 ?
                Arrays.asList(Violation.builder()
                        .field(FIELD)
                        .validationMessage(LENGTH_RULE)
                        .build()) :
                Collections.<Violation>emptyList();
    }
}
