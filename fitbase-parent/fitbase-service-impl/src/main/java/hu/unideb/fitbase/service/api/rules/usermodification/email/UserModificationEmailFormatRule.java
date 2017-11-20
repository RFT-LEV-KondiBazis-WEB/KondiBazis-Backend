package hu.unideb.fitbase.service.api.rules.usermodification.email;

import com.google.common.base.Strings;
import hu.unideb.fitbase.commons.pojo.validator.Violation;
import hu.unideb.fitbase.service.api.domain.UserModification;
import hu.unideb.fitbase.service.api.validator.rule.Rule;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static hu.unideb.fitbase.commons.constants.rules.registration.email.EmailRuleConstants.*;

/**
 * Email format rule.
 **/
@Component
public class UserModificationEmailFormatRule implements Rule<UserModification> {
    @Override
    public List<Violation> validate(UserModification userModification) {
        return Strings.isNullOrEmpty(userModification.getEmail()) || isEmailFormat(userModification) ?
                Collections.<Violation>emptyList() :
                Arrays.asList(Violation.builder()
                        .field(FIELD)
                        .validationMessage(VALIDATION_MESSAGE)
                        .build());
    }

    private boolean isEmailFormat(UserModification request) {
        return request.getEmail().trim().matches(EMAIL_FORMAT_REGEX);
    }
}
