package hu.unideb.fitbase.service.api.rules.pass.duration;

import hu.unideb.fitbase.commons.pojo.validator.Violation;
import hu.unideb.fitbase.service.api.domain.Pass;
import hu.unideb.fitbase.service.api.validator.rule.Rule;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class PassDurationShouldBeUniqueRule implements Rule<Pass> {

    @Override
    public List<Violation> validate(Pass request){
        List<Violation> result = Collections.<Violation>emptyList();
        Integer price = request.getDuration();
        if (price == null) {
            result = Arrays.asList(Violation.builder()
                    .field("pass_duration")
                    .validationMessage("pass not add duration")
                    .build());
        }
        return result;
    }
}
