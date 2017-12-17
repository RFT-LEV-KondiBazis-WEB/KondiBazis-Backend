package hu.unideb.fitbase.service.api.rules.pass.duration;

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


@Component
public class PassDurationsFormatRule implements Rule<PassCreateRequest> {

    @Override
    public List<Violation> validate(PassCreateRequest request) {
        List<Violation> result = Collections.<Violation>emptyList();
        if ((!isNumberFormat(request)) && request.getPassType().equals(PassType.SUITABLE.name())) {
            result = Arrays.asList(Violation.builder()
                    .field(FIELD)
                    .validationMessage(ONLY_NUMBER_ADD)
                    .build());
        }
        return result;
    }

    private boolean isNumberFormat(PassCreateRequest pass) {
        return pass.getDuration().matches(PASS_ONLY_NUMBER_REGEX);
    }
}
