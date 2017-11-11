package hu.unideb.fitbase.service.api.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import hu.unideb.fitbase.persistence.entity.GymEntity;
import hu.unideb.fitbase.service.api.domain.Gym;

@Component
public class GymToGymEntityConverter implements Converter<Gym, GymEntity>{

	@Override
	public GymEntity convert(Gym source) {
		return GymEntity.builder()
				.name(source.getName())
				.city(source.getCity())
				.address(source.getAddress())
				.zipCode(source.getZipCode())
				.description(source.getDescription())
				.openingHours(source.getOpeningHours())
				.build();
	}

}
