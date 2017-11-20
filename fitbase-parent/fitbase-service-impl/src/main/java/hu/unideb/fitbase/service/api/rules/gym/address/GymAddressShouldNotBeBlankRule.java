package hu.unideb.fitbase.service.api.rules.gym.address;

import static hu.unideb.fitbase.commons.constants.rules.gym.address.AddressRuleConstans.FIELD;
import static hu.unideb.fitbase.commons.constants.rules.gym.address.AddressRuleConstans.BLANK_RULE;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import com.google.common.base.Strings;

import hu.unideb.fitbase.commons.pojo.validator.Violation;
import hu.unideb.fitbase.service.api.domain.Gym;
import hu.unideb.fitbase.service.api.validator.rule.Rule;

/**
 * Validates GYM address should not be blank.
 */
@Component
public class GymAddressShouldNotBeBlankRule implements Rule<Gym>{

	@Override
	public List<Violation> validate(Gym gym) {
		return Strings.isNullOrEmpty(gym.getAddress())
				? Arrays.asList(Violation.builder().field(FIELD).validationMessage(BLANK_RULE).build())
				: Collections.<Violation>emptyList();
	}

}
