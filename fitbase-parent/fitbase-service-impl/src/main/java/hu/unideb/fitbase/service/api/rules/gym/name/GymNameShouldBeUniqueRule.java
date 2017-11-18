package hu.unideb.fitbase.service.api.rules.gym.name;

import static hu.unideb.fitbase.commons.constants.rules.gym.name.NameRuleConstants.FIELD;
import static hu.unideb.fitbase.commons.constants.rules.gym.name.NameRuleConstants.UNIQUE_RULE;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hu.unideb.fitbase.commons.pojo.validator.Violation;
import hu.unideb.fitbase.service.api.domain.Gym;
import hu.unideb.fitbase.service.api.service.GymService;
import hu.unideb.fitbase.service.api.validator.rule.Rule;

/**
 * Validates GYM name should be unique.
 */
@Component
public class GymNameShouldBeUniqueRule implements Rule<Gym> {

	@Autowired
	private GymService gymService;

	@Override
	public List<Violation> validate(Gym gym) {
		List<Violation> result = Collections.<Violation>emptyList();
		String name = gym.getName();
		if(name != null) {
			Gym findedGym=gymService.findByName(name);
			if (findedGym!=null) {
				result = Arrays.asList(Violation.builder()
				        .field(FIELD)
				        .validationMessage(UNIQUE_RULE)
				        .build());
			}
			
		}
		return result;
	}
}
