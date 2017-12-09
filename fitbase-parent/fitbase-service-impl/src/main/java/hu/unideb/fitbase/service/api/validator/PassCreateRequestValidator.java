package hu.unideb.fitbase.service.api.validator;

import hu.unideb.fitbase.service.api.domain.Pass;
import hu.unideb.fitbase.service.api.validator.rule.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PassCreateRequestValidator extends AbstractValidator<Pass> {

    @Autowired
    public PassCreateRequestValidator(List<Rule<Pass>> rules){
        super(rules);
    }
}
