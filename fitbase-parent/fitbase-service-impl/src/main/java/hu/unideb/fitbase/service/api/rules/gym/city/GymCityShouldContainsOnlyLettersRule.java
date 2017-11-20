package hu.unideb.fitbase.service.api.rules.gym.city;

import static hu.unideb.fitbase.commons.constants.rules.gym.city.CityRuleConstants.FIELD;
import static hu.unideb.fitbase.commons.constants.rules.gym.city.CityRuleConstants.LETTER_RULE;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import hu.unideb.fitbase.commons.pojo.validator.Violation;
import hu.unideb.fitbase.service.api.domain.Gym;
import hu.unideb.fitbase.service.api.validator.rule.Rule;

/**
 * Validates GYM vity should contains only letters.
 */
@Component
public class GymCityShouldContainsOnlyLettersRule implements Rule<Gym> {

	protected static final String MATCHER = "[A-Za-z]+";

	@Override
	public List<Violation> validate(Gym gym) {
		return gym.getCity() != null && gym.getCity().matches(MATCHER) ? Collections.<Violation>emptyList()
				: Arrays.asList(Violation.builder().field(FIELD).validationMessage(LETTER_RULE).build());
	}

}
