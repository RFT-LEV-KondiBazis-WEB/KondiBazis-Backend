package hu.unideb.fitbase.service.api.rules.pass.name;

import com.google.common.base.Strings;
import hu.unideb.fitbase.commons.pojo.request.PassCreateRequest;
import hu.unideb.fitbase.commons.pojo.validator.Violation;
import hu.unideb.fitbase.service.api.domain.Pass;
import hu.unideb.fitbase.service.api.validator.rule.Rule;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static hu.unideb.fitbase.commons.constants.rules.pass.name.PassNameValidateMessages.BLANK_RULE;
import static hu.unideb.fitbase.commons.constants.rules.pass.name.PassNameValidateMessages.FIELD;


@Component
public class PassNameShouldNotBeBlankRule implements Rule<PassCreateRequest>{

    @Override
    public List<Violation> validate(PassCreateRequest request) {
        return Strings.isNullOrEmpty(request.getName()) ?
                Arrays.asList(Violation.builder()
                        .field(FIELD)
                        .validationMessage(BLANK_RULE)
                        .build()) :
                Collections.<Violation>emptyList();
    }
}
