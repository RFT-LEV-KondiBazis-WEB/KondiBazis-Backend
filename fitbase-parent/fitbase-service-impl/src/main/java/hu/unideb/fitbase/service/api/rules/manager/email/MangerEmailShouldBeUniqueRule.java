package hu.unideb.fitbase.service.api.rules.manager.email;

import hu.unideb.fitbase.commons.pojo.exceptions.BaseException;
import hu.unideb.fitbase.commons.pojo.exceptions.ViolationException;
import hu.unideb.fitbase.commons.pojo.request.ManagerRegistrationRequest;
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
public class MangerEmailShouldBeUniqueRule implements Rule<ManagerRegistrationRequest> {

    @Autowired
    private UserService userService;

    @Override
    public List<Violation> validate(ManagerRegistrationRequest request) {
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