package hu.unideb.fitbase.service.api.rules.pass.duration;

import hu.unideb.fitbase.commons.pojo.validator.Violation;
import hu.unideb.fitbase.service.api.domain.Pass;
import hu.unideb.fitbase.service.api.validator.rule.Rule;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static hu.unideb.fitbase.commons.constants.rules.pass.duration.PassTimeDurationConstants.BLANK_RULE;
import static hu.unideb.fitbase.commons.constants.rules.pass.duration.PassTimeDurationConstants.FIELD;

@Component
public class PassTimeDurationNotBeBlankRule implements Rule<Pass> {

    @Override
    public List<Violation> validate(Pass request){
        List<Violation> result = Collections.<Violation>emptyList();
        Integer timeDuration = request.getTimeDuration();
        if (timeDuration == null) {
            result = Arrays.asList(Violation.builder()
                    .field(FIELD)
                    .validationMessage(BLANK_RULE)
                    .build());
        }
        return result;
    }
}
