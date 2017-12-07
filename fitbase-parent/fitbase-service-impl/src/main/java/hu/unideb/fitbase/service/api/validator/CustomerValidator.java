package hu.unideb.fitbase.service.api.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hu.unideb.fitbase.service.api.domain.Customer;
import hu.unideb.fitbase.service.api.validator.rule.Rule;

@Component
public class CustomerValidator extends AbstractValidator<Customer>{

	@Autowired
	public CustomerValidator(List<Rule<Customer>> rules) {
		super(rules);
	}
}
