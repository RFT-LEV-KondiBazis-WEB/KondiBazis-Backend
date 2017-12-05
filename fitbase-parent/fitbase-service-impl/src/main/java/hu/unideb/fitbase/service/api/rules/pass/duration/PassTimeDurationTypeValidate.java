package hu.unideb.fitbase.service.api.rules.pass.duration;

import hu.unideb.fitbase.commons.pojo.enumeration.PassTimeDurationType;
import hu.unideb.fitbase.commons.pojo.validator.Violation;
import hu.unideb.fitbase.service.api.domain.Pass;
import hu.unideb.fitbase.service.api.validator.rule.Rule;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static hu.unideb.fitbase.commons.constants.rules.pass.duration.type.PassTypeDuration.FIELD;
import static hu.unideb.fitbase.commons.constants.rules.pass.duration.type.PassTypeDuration.PASS_TYPE_DURATION;

@Component
public class PassTimeDurationTypeValidate implements Rule<Pass> {

    @Override
    public List<Violation> validate(Pass request) {
        return enumTypeContains(request.getPassTimeDurationType()) ?
                Collections.<Violation>emptyList() :
                Arrays.asList(Violation.builder()
                        .field(FIELD)
                        .validationMessage(PASS_TYPE_DURATION)
                        .build());
    }

    private boolean enumTypeContains(String enumString) {
        try {
            PassTimeDurationType.valueOf(enumString);
        } catch (IllegalArgumentException iae) {
            return false;
        }
        return true;
    }
}
