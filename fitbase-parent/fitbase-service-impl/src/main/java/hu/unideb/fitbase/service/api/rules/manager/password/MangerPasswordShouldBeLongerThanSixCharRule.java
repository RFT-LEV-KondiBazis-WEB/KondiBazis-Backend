package hu.unideb.fitbase.service.api.rules.manager.password;

import hu.unideb.fitbase.commons.pojo.request.ManagerRegistrationRequest;
import hu.unideb.fitbase.commons.pojo.request.RegistrationRequest;
import hu.unideb.fitbase.commons.pojo.validator.Violation;
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
public class MangerPasswordShouldBeLongerThanSixCharRule implements Rule<ManagerRegistrationRequest> {

    @Override
    public List<Violation> validate(ManagerRegistrationRequest request) {
        return request.getPassword() != null && request.getPasswordConfirm().length() < 6 ?
                Arrays.asList(Violation.builder()
                        .field(FIELD)
                        .validationMessage(LENGTH_RULE)
                        .build()) :
                Collections.<Violation>emptyList();
    }
}
