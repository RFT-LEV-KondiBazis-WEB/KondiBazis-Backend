package hu.unideb.fitbase.service.api.validator;

import hu.unideb.fitbase.commons.pojo.request.RegistrationRequest;
import hu.unideb.fitbase.service.api.validator.rule.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RegistrationRequestValidator extends AbstractValidator<RegistrationRequest> {

    @Autowired
    public RegistrationRequestValidator(List<Rule<RegistrationRequest>> rules) {
        super(rules);
    }

}