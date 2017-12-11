package hu.unideb.fitbase.service.api.rules.manager.username;

import hu.unideb.fitbase.commons.pojo.request.ManagerRegistrationRequest;
import hu.unideb.fitbase.commons.pojo.request.RegistrationRequest;
import hu.unideb.fitbase.commons.pojo.validator.Violation;
import hu.unideb.fitbase.service.api.validator.rule.Rule;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static hu.unideb.fitbase.commons.constants.rules.registration.username.UsernameRuleConstants.FIELD;
import static hu.unideb.fitbase.commons.constants.rules.registration.username.UsernameRuleConstants.LENGTH_RULE;

/**
 * Validates username length.
 */
@Component
public class UsernameShouldBeLongerThanFourCharRule implements Rule<ManagerRegistrationRequest> {

    @Override
    public List<Violation> validate(ManagerRegistrationRequest request) {
        return request.getUsername() != null && request.getUsername().length() < 4 ?
                Arrays.asList(Violation.builder()
                        .field(FIELD)
                        .validationMessage(LENGTH_RULE)
                        .build()) :
                Collections.<Violation>emptyList();
    }
}
