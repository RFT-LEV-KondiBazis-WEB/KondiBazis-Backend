package hu.unideb.fitbase.service.api.rules.usermodification.password;

import hu.unideb.fitbase.commons.pojo.validator.Violation;
import hu.unideb.fitbase.service.api.domain.UserModification;
import hu.unideb.fitbase.service.api.validator.rule.Rule;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static hu.unideb.fitbase.commons.constants.rules.registration.password.PasswordRuleConstants.CONFIRMATION_RULE;
import static hu.unideb.fitbase.commons.constants.rules.registration.password.PasswordRuleConstants.FIELD;

@Component
public class UserModificationPasswordConfirmationRule implements Rule<UserModification> {

    @Override
    public List<Violation> validate(UserModification userModification) {
        List<Violation> result = Collections.<Violation>emptyList();
        List<Violation> violations = Arrays.asList(Violation.builder()
                .field(FIELD)
                .validationMessage(CONFIRMATION_RULE)
                .build());
        if (userModification.getPassword() != null && userModification.getPasswordConfirm() == null) {
            return violations;
        }
        if (userModification.getPassword() != null && userModification.getPasswordConfirm() != null && !userModification
                .getPassword().equals(userModification.getPasswordConfirm())) {
            return violations;
        }
        return result;
    }
}
