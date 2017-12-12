package hu.unideb.fitbase.service.api.rules.manager.password;

import hu.unideb.fitbase.commons.pojo.request.ManagerRegistrationRequest;
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
public class MangerPasswordConfirmationRule implements Rule<ManagerRegistrationRequest> {

    @Override
    public List<Violation> validate(ManagerRegistrationRequest request) {
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
