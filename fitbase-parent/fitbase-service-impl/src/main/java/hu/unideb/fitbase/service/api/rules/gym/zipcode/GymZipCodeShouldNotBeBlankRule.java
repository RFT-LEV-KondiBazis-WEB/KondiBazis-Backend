package hu.unideb.fitbase.service.api.rules.gym.zipcode;

import static hu.unideb.fitbase.commons.constants.rules.gym.zipcode.ZipCodeRuleConstans.FIELD;
import static hu.unideb.fitbase.commons.constants.rules.gym.zipcode.ZipCodeRuleConstans.BLANK_RULE;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.google.common.base.Strings;
import org.springframework.stereotype.Component;

import hu.unideb.fitbase.commons.pojo.validator.Violation;
import hu.unideb.fitbase.service.api.domain.Gym;
import hu.unideb.fitbase.service.api.validator.rule.Rule;

/**
 * Validates GYM ZipCode should not be blank.
 */
@Component
public class GymZipCodeShouldNotBeBlankRule implements Rule<Gym> {

	@Override
	public List<Violation> validate(Gym gym) {
		return Strings.isNullOrEmpty(gym.getZipCode())
				? Arrays.asList(Violation.builder().field(FIELD).validationMessage(BLANK_RULE).build())
				: Collections.<Violation>emptyList();
	}
}