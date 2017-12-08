package hu.unideb.fitbase.service.api.rules.pass.duration;

import hu.unideb.fitbase.commons.pojo.enumeration.PassType;
import hu.unideb.fitbase.commons.pojo.validator.Violation;
import hu.unideb.fitbase.service.api.domain.Pass;
import hu.unideb.fitbase.service.api.validator.rule.Rule;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static hu.unideb.fitbase.commons.constants.rules.pass.duration.PassDurationConstants.BLANK_RULE;
import static hu.unideb.fitbase.commons.constants.rules.pass.duration.PassDurationConstants.FIELD;

@Component
public class PassDurationAndSuitableTypeValidate implements Rule<Pass> {

    @Override
    public List<Violation> validate(Pass request){
        List<Violation> result = Collections.<Violation>emptyList();
        Integer duration = request.getDuration();
        if (duration == null && request.getPassType().equals(PassType.SUITABLE.name())) {
            result = Arrays.asList(Violation.builder()
                    .field(FIELD)
                    .validationMessage(BLANK_RULE)
                    .build());
        }
        return result;
    }

}
