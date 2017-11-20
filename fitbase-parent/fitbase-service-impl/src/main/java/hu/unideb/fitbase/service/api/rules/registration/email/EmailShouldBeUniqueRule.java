package hu.unideb.fitbase.service.api.rules.registration.email;

import hu.unideb.fitbase.commons.pojo.exceptions.BaseException;
import hu.unideb.fitbase.commons.pojo.request.RegistrationRequest;
import hu.unideb.fitbase.commons.pojo.validator.Violation;
import hu.unideb.fitbase.service.api.exception.EntityNotFoundException;
import hu.unideb.fitbase.service.api.service.UserService;
import hu.unideb.fitbase.service.api.validator.rule.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static hu.unideb.fitbase.commons.constants.rules.registration.email.EmailRuleConstants.FIELD;
import static hu.unideb.fitbase.commons.constants.rules.registration.email.EmailRuleConstants.UNIQUE_RULE;

@Component
public class EmailShouldBeUniqueRule implements Rule<RegistrationRequest> {

    @Autowired
    private UserService userService;

    @Override
    public List<Violation> validate(RegistrationRequest request) {
        List<Violation> result = Collections.<Violation>emptyList();
        String email = request.getEmail();
        if (email != null) {
            try {
                try {
                    userService.findByEmail(email);
                    result = Arrays.asList(Violation.builder()
                            .field(FIELD)
                            .validationMessage(UNIQUE_RULE)
                            .build());
                } catch (EntityNotFoundException e) {
                    result = Collections.<Violation>emptyList();
                }
            } catch (BaseException e) {
                e.printStackTrace();
            }

        }
        return result;
    }
}