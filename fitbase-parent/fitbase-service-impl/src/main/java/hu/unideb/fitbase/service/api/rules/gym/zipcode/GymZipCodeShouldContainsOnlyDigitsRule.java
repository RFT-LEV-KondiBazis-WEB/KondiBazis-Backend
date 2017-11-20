package hu.unideb.fitbase.service.api.rules.gym.zipcode;

import static hu.unideb.fitbase.commons.constants.rules.gym.zipcode.ZipCodeRuleConstans.FIELD;
import static hu.unideb.fitbase.commons.constants.rules.gym.zipcode.ZipCodeRuleConstans.DIGIT_RULE;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import hu.unideb.fitbase.commons.pojo.request.GymRequest;
import hu.unideb.fitbase.commons.pojo.validator.Violation;
import hu.unideb.fitbase.service.api.domain.Gym;
import hu.unideb.fitbase.service.api.validator.rule.Rule;


/**
 * Validates GYM ZipCode should countains only digits.
 */
@Component
public class GymZipCodeShouldContainsOnlyDigitsRule implements Rule<Gym>{

	protected static final String MATCHER = "[0-9]{4}";
	
	@Override
	public List<Violation> validate(Gym gym) {
		return gym.getZipCode() != null && gym.getZipCode().matches(MATCHER) ? Collections.<Violation>emptyList()
				: Arrays.asList(Violation.builder().field(FIELD).validationMessage(DIGIT_RULE).build());
	}

}
