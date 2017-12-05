package hu.unideb.fitbase.service.api.rules.usermodification.email;

import hu.unideb.fitbase.commons.pojo.exceptions.BaseException;
import hu.unideb.fitbase.commons.pojo.validator.Violation;
import hu.unideb.fitbase.service.api.domain.User;
import hu.unideb.fitbase.service.api.domain.UserModification;
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
public class UserModificationEmailShouldBeUniqueRule implements Rule<UserModification> {

    @Autowired
    private UserService userService;

    @Override
    public List<Violation> validate(UserModification userModification) {
        List<Violation> result = Collections.<Violation>emptyList();
        String email = userModification.getEmail();
        Long id = userModification.getId();

        if (email != null) {
            try {
                try {

                    User findedCustomer = userService.findByEmail(email);
                    if( findedCustomer != null ) {
                        if(!findedCustomer.getId().equals(id) ) {
                            result = Arrays.asList(Violation.builder().field(FIELD).validationMessage(UNIQUE_RULE).build());

                        }
                    }
//                    userService.findByEmail(email);
//                    result = Arrays.asList(Violation.builder()
//                            .field(FIELD)
//                            .validationMessage(UNIQUE_RULE)
//                            .build());
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