package hu.unideb.fitbase.service.api.rules.pass.duration;

import hu.unideb.fitbase.commons.pojo.request.PassCreateRequest;
import hu.unideb.fitbase.commons.pojo.validator.Violation;
import hu.unideb.fitbase.service.api.validator.rule.Rule;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static hu.unideb.fitbase.commons.constants.rules.pass.duration.PassDurationConstants.PASS_NOT_ADD_NULL;
import static hu.unideb.fitbase.commons.constants.rules.pass.duration.PassTimeDurationConstants.BLANK_RULE;
import static hu.unideb.fitbase.commons.constants.rules.pass.duration.PassTimeDurationConstants.FIELD;

@Component
public class PassTimeDurationNotNull implements Rule<PassCreateRequest> {

    @Override
    public List<Violation> validate(PassCreateRequest request){
        List<Violation> result = Collections.<Violation>emptyList();
        String timeDuration = request.getTimeDuration();
        System.out.println(timeDuration);
        if (timeDuration.equals("0") ) {
            result = Arrays.asList(Violation.builder()
                    .field(FIELD)
                    .validationMessage(PASS_NOT_ADD_NULL)
                    .build());
        }
        return result;
    }
}
