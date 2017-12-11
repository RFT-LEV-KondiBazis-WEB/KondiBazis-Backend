package hu.unideb.fitbase.service.api.validator;

import hu.unideb.fitbase.commons.pojo.request.ManagerRegistrationRequest;
import hu.unideb.fitbase.service.api.validator.rule.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManagerRegistrationRequestValidator extends AbstractValidator<ManagerRegistrationRequest> {

    @Autowired
    public ManagerRegistrationRequestValidator(List<Rule<ManagerRegistrationRequest>> rules){
        super(rules);
    }
}
