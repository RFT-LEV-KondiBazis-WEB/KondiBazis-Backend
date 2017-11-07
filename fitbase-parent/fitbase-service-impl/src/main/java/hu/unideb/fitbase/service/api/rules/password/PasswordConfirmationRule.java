package hu.unideb.fitbase.service.api.rules.password;

import hu.unideb.fitbase.commons.pojo.request.RegistrationRequest;
import hu.unideb.fitbase.commons.pojo.validator.Violation;
import hu.unideb.fitbase.service.api.validator.rule.Rule;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static hu.unideb.fitbase.commons.constants.rules.registration.password.PasswordRuleConstants.CONFIRMATION_RULE;
import static hu.unideb.fitbase.commons.constants.rules.registration.password.PasswordRuleConstants.FIELD;

@Component
public class PasswordConfirmationRule implements Rule<RegistrationRequest> {

    @Override
    public List<Violation> validate(RegistrationRequest request) {
        List<Violation> result = Collections.<Violation>emptyList();
        if (request.getPassword() != null && request.getPasswordConfirm() != null && !request
                .getPassword().equals(request.getPasswordConfirm())) {
            return Arrays.asList(Violation.builder()
                    .field(FIELD)
                    .validationMessage(CONFIRMATION_RULE)
                    .build());
        }
        return result;
    }
}
