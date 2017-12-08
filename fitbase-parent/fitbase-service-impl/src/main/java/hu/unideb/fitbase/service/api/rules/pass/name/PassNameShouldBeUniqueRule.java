package hu.unideb.fitbase.service.api.rules.pass.name;

import hu.unideb.fitbase.commons.pojo.exceptions.ViolationException;
import hu.unideb.fitbase.commons.pojo.validator.Violation;
import hu.unideb.fitbase.service.api.domain.Pass;
import hu.unideb.fitbase.service.api.service.PassService;
import hu.unideb.fitbase.service.api.validator.rule.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class PassNameShouldBeUniqueRule implements Rule<Pass> {

    @Autowired
    private PassService passService;

    @Override
    public List<Violation> validate(Pass request){
        List<Violation> result = Collections.<Violation>emptyList();
        String name = request.getName();
        if(name != null) {
            Pass findedPass = passService.findPassByName(name);
            if (findedPass!=null) {
                result = Arrays.asList(Violation.builder()
                        .field("pass_name")
                        .validationMessage("pass nameeee")
                        .build());
            }

        }
        return result;
    }
}