package hu.unideb.fitbase.service.api.rules.email;

import com.google.common.base.Strings;
import hu.unideb.fitbase.commons.pojo.request.RegistrationRequest;
import hu.unideb.fitbase.commons.pojo.validator.Violation;
import hu.unideb.fitbase.service.api.validator.rule.Rule;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static hu.unideb.fitbase.commons.constants.rules.registration.email.EmailRuleConstants.EMAIL_FORMAT_REGEX;
import static hu.unideb.fitbase.commons.constants.rules.registration.email.EmailRuleConstants.FIELD;
import static hu.unideb.fitbase.commons.constants.rules.registration.email.EmailRuleConstants.VALIDATION_MESSAGE;

/**
 * Email format rule.
 **/
@Component
public class EmailFormatRule implements Rule<RegistrationRequest> {
    @Override
    public List<Violation> validate(RegistrationRequest request) {
        return Strings.isNullOrEmpty(request.getEmail()) || isEmailFormat(request) ?
                Collections.<Violation>emptyList() :
                Arrays.asList(Violation.builder()
                        .field(FIELD)
                        .validationMessage(VALIDATION_MESSAGE)
                        .build());
    }

    private boolean isEmailFormat(RegistrationRequest request) {
        return request.getEmail().trim().matches(EMAIL_FORMAT_REGEX);
    }
}
