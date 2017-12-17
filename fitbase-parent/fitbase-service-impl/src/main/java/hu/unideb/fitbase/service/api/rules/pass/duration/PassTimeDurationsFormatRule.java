package hu.unideb.fitbase.service.api.rules.pass.duration;

import com.google.common.base.Strings;
import hu.unideb.fitbase.commons.pojo.enumeration.PassType;
import hu.unideb.fitbase.commons.pojo.request.PassCreateRequest;
import hu.unideb.fitbase.commons.pojo.validator.Violation;
import hu.unideb.fitbase.service.api.domain.Pass;
import hu.unideb.fitbase.service.api.validator.rule.Rule;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static hu.unideb.fitbase.commons.constants.rules.pass.duration.PassDurationConstants.*;
import static hu.unideb.fitbase.commons.constants.rules.pass.duration.PassTimeDurationConstants.FIELD;

@Component
public class PassTimeDurationsFormatRule implements Rule<PassCreateRequest> {

    @Override
    public List<Violation> validate(PassCreateRequest request){
//        return Strings.isNullOrEmpty(String.valueOf(request.getTimeDuration())) || (!isNumberFormat(request)) ?
//                Collections.<Violation>emptyList() :
//                Arrays.asList(Violation.builder()
//                        .field(FIELD)
//                        .validationMessage(ONLY_NUMBER_ADD)
//                        .build());
        List<Violation> result = Collections.<Violation>emptyList();
        if (!isNumberFormat(request)) {
            result = Arrays.asList(Violation.builder()
                    .field(FIELD)
                    .validationMessage(ONLY_NUMBER_ADD)
                    .build());
        }
        return result;
    }

    private boolean isNumberFormat(PassCreateRequest pass) {
        return pass.getTimeDuration().matches(PASS_ONLY_NUMBER_REGEX);
    }
}
