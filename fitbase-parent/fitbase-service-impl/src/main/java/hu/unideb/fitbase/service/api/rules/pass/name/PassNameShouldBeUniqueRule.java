package hu.unideb.fitbase.service.api.rules.pass.name;

import hu.unideb.fitbase.commons.pojo.exceptions.BaseException;
import hu.unideb.fitbase.commons.pojo.request.PassCreateRequest;
import hu.unideb.fitbase.commons.pojo.validator.Violation;
import hu.unideb.fitbase.service.api.domain.Pass;
import hu.unideb.fitbase.service.api.service.PassService;
import hu.unideb.fitbase.service.api.validator.rule.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static hu.unideb.fitbase.commons.constants.rules.pass.name.PassNameValidateMessages.FIELD;
import static hu.unideb.fitbase.commons.constants.rules.pass.name.PassNameValidateMessages.PASS_NAME;

@Component
public class PassNameShouldBeUniqueRule implements Rule<PassCreateRequest> {

    @Autowired
    private PassService passService;

    @Override
    public List<Violation> validate(PassCreateRequest request) throws BaseException {
        List<Violation> result = Collections.<Violation>emptyList();
        String name = request.getName();
        if(name != null) {
            Pass findedPass = passService.findPassByName(name);
            if (findedPass!=null) {
                result = Arrays.asList(Violation.builder()
                        .field(FIELD)
                        .validationMessage(PASS_NAME)
                        .build());
            }

        }
        return result;
    }
}
