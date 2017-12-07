package hu.unideb.fitbase.service.api.rules.customer.email;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import com.google.common.base.Strings;

import hu.unideb.fitbase.commons.pojo.validator.Violation;
import hu.unideb.fitbase.service.api.domain.Customer;
import hu.unideb.fitbase.service.api.validator.rule.Rule;

import static hu.unideb.fitbase.commons.constants.rules.customer.email.EmailRuleConstants.FIELD;
import static hu.unideb.fitbase.commons.constants.rules.customer.email.EmailRuleConstants.VALIDATION_MESSAGE;
import static hu.unideb.fitbase.commons.constants.rules.customer.email.EmailRuleConstants.EMAIL_FORMAT_REGEX;

/**
 * Email format rule.
 **/

@Component
public class CustomerEmailFormatRule implements Rule<Customer>{

	@Override
	public List<Violation> validate(Customer customer) {
        return Strings.isNullOrEmpty(customer.getEmail()) || isEmailFormat(customer) ?
                Collections.<Violation>emptyList() :
                Arrays.asList(Violation.builder()
                        .field(FIELD)
                        .validationMessage(VALIDATION_MESSAGE)
                        .build());
	}
	
    private boolean isEmailFormat(Customer customer) {
        return customer.getEmail().trim().matches(EMAIL_FORMAT_REGEX);
    }	

}
