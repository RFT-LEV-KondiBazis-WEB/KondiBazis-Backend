package hu.unideb.fitbase.service.api.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hu.unideb.fitbase.service.api.domain.Gym;
import hu.unideb.fitbase.service.api.validator.rule.Rule;

@Component
public class GymValidator extends AbstractValidator<Gym> {

	@Autowired
	public GymValidator(List<Rule<Gym>> rules) {
		super(rules);
	}
}
