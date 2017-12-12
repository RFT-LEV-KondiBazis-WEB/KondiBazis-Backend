package hu.unideb.fitbase.service.api.rules.manager.password;

import com.google.common.base.Strings;
import hu.unideb.fitbase.commons.pojo.request.ManagerRegistrationRequest;
import hu.unideb.fitbase.commons.pojo.request.RegistrationRequest;
import hu.unideb.fitbase.commons.pojo.validator.Violation;
import hu.unideb.fitbase.service.api.validator.rule.Rule;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static hu.unideb.fitbase.commons.constants.rules.registration.password.PasswordRuleConstants.BLANK_RULE;
import static hu.unideb.fitbase.commons.constants.rules.registration.password.PasswordRuleConstants.FIELD;

/**
 * Validates password should not be blank.
 */
@Component
public class MangerPasswordShouldNotBeBlankRule implements Rule<ManagerRegistrationRequest> {

    @Override
    public List<Violation> validate(ManagerRegistrationRequest request) {
        return Strings.isNullOrEmpty(request.getPassword()) ?
                Arrays.asList(Violation.builder()
                        .field(FIELD)
                        .validationMessage(BLANK_RULE)
                        .build()) :
                Collections.<Violation>emptyList();
    }
}
