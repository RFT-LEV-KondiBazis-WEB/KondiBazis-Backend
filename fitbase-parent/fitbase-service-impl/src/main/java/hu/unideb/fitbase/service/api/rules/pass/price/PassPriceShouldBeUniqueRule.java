package hu.unideb.fitbase.service.api.rules.pass.price;

import hu.unideb.fitbase.commons.pojo.request.PassCreateRequest;
import hu.unideb.fitbase.commons.pojo.validator.Violation;
import hu.unideb.fitbase.service.api.domain.Pass;
import hu.unideb.fitbase.service.api.validator.rule.Rule;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static hu.unideb.fitbase.commons.constants.rules.pass.price.PassPriceValidateMessages.FIELD;
import static hu.unideb.fitbase.commons.constants.rules.pass.price.PassPriceValidateMessages.PASS_PRICE;

@Component
public class PassPriceShouldBeUniqueRule implements Rule<PassCreateRequest> {

    @Override
    public List<Violation> validate(PassCreateRequest request){
        List<Violation> result = Collections.<Violation>emptyList();
        Integer price = request.getPrice();
        if (price == null) {
            result = Arrays.asList(Violation.builder()
                    .field(FIELD)
                    .validationMessage(PASS_PRICE)
                    .build());
        }
        return result;
    }
}
