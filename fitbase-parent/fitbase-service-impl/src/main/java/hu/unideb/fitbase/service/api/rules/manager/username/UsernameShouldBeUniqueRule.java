package hu.unideb.fitbase.service.api.rules.manager.username;

import hu.unideb.fitbase.commons.pojo.request.ManagerRegistrationRequest;
import hu.unideb.fitbase.commons.pojo.request.RegistrationRequest;
import hu.unideb.fitbase.commons.pojo.validator.Violation;
import hu.unideb.fitbase.service.api.domain.User;
import hu.unideb.fitbase.service.api.service.UserService;
import hu.unideb.fitbase.service.api.validator.rule.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static hu.unideb.fitbase.commons.constants.rules.registration.username.UsernameRuleConstants.FIELD;
import static hu.unideb.fitbase.commons.constants.rules.registration.username.UsernameRuleConstants.UNIQUE_RULE;

/**
 * Validates username uniqueness.
 */
@Component
public class UsernameShouldBeUniqueRule implements Rule<ManagerRegistrationRequest> {

    @Autowired
    private UserService userService;

    @Override
    public List<Violation> validate(ManagerRegistrationRequest request) {
        List<Violation> result = Collections.<Violation>emptyList();
        String username = request.getUsername();
        User user;
        if (username != null) {
            user = userService.findByUsername(username);
            if (user != null) {
                result = Arrays.asList(Violation.builder()
                        .field(FIELD)
                        .validationMessage(UNIQUE_RULE)
                        .build());
            }
        }
        return result;
    }
}
