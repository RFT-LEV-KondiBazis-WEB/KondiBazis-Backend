package hu.unideb.fitbase.service.api.rules.pass.type;

import hu.unideb.fitbase.commons.pojo.enumeration.PassType;
import hu.unideb.fitbase.commons.pojo.validator.Violation;
import hu.unideb.fitbase.service.api.domain.Pass;
import hu.unideb.fitbase.service.api.validator.rule.Rule;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static hu.unideb.fitbase.commons.constants.rules.pass.type.PassTypeValidateMessages.FIELD;
import static hu.unideb.fitbase.commons.constants.rules.pass.type.PassTypeValidateMessages.PASS_TYPE;


@Component
public class PassTypeValidate implements Rule<Pass> {

    @Override
    public List<Violation> validate(Pass request) {
        return enumTypeContains(request.getPassType()) ?
                Collections.<Violation>emptyList() :
                Arrays.asList(Violation.builder()
                        .field(FIELD)
                        .validationMessage(PASS_TYPE)
                        .build());
    }

    private boolean enumTypeContains(String enumString) {
        try {
            PassType.valueOf(enumString);
        } catch (IllegalArgumentException iae) {
            return false;
        }
        return true;
    }
}
