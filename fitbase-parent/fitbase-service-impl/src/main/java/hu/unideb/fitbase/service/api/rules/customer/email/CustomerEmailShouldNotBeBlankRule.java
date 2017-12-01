package hu.unideb.fitbase.service.api.rules.customer.email;

import static hu.unideb.fitbase.commons.constants.rules.registration.email.EmailRuleConstants.BLANK_RULE;
import static hu.unideb.fitbase.commons.constants.rules.registration.email.EmailRuleConstants.FIELD;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import com.google.common.base.Strings;

import hu.unideb.fitbase.commons.pojo.validator.Violation;
import hu.unideb.fitbase.service.api.domain.Customer;
import hu.unideb.fitbase.service.api.validator.rule.Rule;

/**
 * Validates email should not be blank.
 */

@Component
public class CustomerEmailShouldNotBeBlankRule implements Rule<Customer>{

	@Override
	public List<Violation> validate(Customer customer) {
	       return Strings.isNullOrEmpty(customer.getEmail()) ?
	                Arrays.asList(Violation.builder()
	                        .field(FIELD)
	                        .validationMessage(BLANK_RULE)
	                        .build()) :
	                Collections.<Violation>emptyList();
	}

}
