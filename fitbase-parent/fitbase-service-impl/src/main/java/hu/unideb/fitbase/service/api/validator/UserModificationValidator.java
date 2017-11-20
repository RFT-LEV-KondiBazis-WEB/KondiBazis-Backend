package hu.unideb.fitbase.service.api.validator;

import hu.unideb.fitbase.service.api.domain.UserModification;
import hu.unideb.fitbase.service.api.validator.rule.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserModificationValidator extends AbstractValidator<UserModification> {

    @Autowired
    public UserModificationValidator(List<Rule<UserModification>> rules) {
        super(rules);
    }
}
