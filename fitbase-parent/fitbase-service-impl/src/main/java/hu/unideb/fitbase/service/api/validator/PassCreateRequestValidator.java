package hu.unideb.fitbase.service.api.validator;

import hu.unideb.fitbase.commons.pojo.request.PassCreateRequest;
import hu.unideb.fitbase.service.api.domain.Pass;
import hu.unideb.fitbase.service.api.validator.rule.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PassCreateRequestValidator extends AbstractValidator<PassCreateRequest> {

    @Autowired
    public PassCreateRequestValidator(List<Rule<PassCreateRequest>> rules){
        super(rules);
    }
}
