package hu.unideb.fitbase.service.api.rules.customer.email;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hu.unideb.fitbase.commons.pojo.validator.Violation;
import hu.unideb.fitbase.service.api.domain.Customer;
import hu.unideb.fitbase.service.api.service.CustomerService;
import hu.unideb.fitbase.service.api.validator.rule.Rule;

import static hu.unideb.fitbase.commons.constants.rules.customer.email.EmailRuleConstants.FIELD;
import static hu.unideb.fitbase.commons.constants.rules.customer.email.EmailRuleConstants.UNIQUE_RULE;

/**
 * Validates email should be unique.
 */

@Component
public class CustomerEmailShouldBeUniqueRule implements Rule<Customer> {

	@Autowired
	private CustomerService customerService;

	@Override
	public List<Violation> validate(Customer customer) {
		List<Violation> result = Collections.<Violation>emptyList();
		String email = customer.getEmail();
		Long id = customer.getId();
		if (email != null) {
			Customer findedCustomer = customerService.findCustomerByEmail(email);
			if( findedCustomer != null ) {
				if(!findedCustomer.getId().equals(id) )
				result = Arrays.asList(Violation.builder().field(FIELD).validationMessage(UNIQUE_RULE).build());
			}
		}
		return result;
	}
}