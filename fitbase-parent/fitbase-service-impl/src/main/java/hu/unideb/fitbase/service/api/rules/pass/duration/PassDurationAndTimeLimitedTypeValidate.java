package hu.unideb.fitbase.service.api.rules.pass.duration;

import hu.unideb.fitbase.commons.pojo.enumeration.PassType;
import hu.unideb.fitbase.commons.pojo.validator.Violation;
import hu.unideb.fitbase.service.api.domain.Pass;
import hu.unideb.fitbase.service.api.validator.rule.Rule;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static hu.unideb.fitbase.commons.constants.rules.pass.duration.PassDurationConstants.FIELD;
import static hu.unideb.fitbase.commons.constants.rules.pass.duration.PassDurationConstants.NOT_ADD_DURATION;

@Component
public class PassDurationAndTimeLimitedTypeValidate implements Rule<Pass> {

    @Override
    public List<Violation> validate(Pass request) {
        List<Violation> result = Collections.<Violation>emptyList();
        Integer duration = request.getDuration();

        if (duration != null && request.getPassType().equals(PassType.TIME_LIMITED.name())) {
            result = Arrays.asList(Violation.builder()
                    .field(FIELD)
                    .validationMessage(NOT_ADD_DURATION)
                    .build());
        }
        return result;
    }
}
