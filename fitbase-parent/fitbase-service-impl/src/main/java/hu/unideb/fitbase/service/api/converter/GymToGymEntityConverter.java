package hu.unideb.fitbase.service.api.converter;

import hu.unideb.fitbase.persistence.entity.UserEntity;
import hu.unideb.fitbase.service.api.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import hu.unideb.fitbase.persistence.entity.GymEntity;
import hu.unideb.fitbase.service.api.domain.Gym;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GymToGymEntityConverter implements Converter<Gym, GymEntity>{

	@Autowired
	UserToUserEntityConverter userToUserEntityConverter;

	@Override
	public GymEntity convert(Gym source) {
		return GymEntity.builder()
				.id(source.getId())
				.name(source.getName())
				.city(source.getCity())
				.address(source.getAddress())
				.zipCode(Integer.parseInt(source.getZipCode()))
				.description(source.getDescription())
				.openingHours(source.getOpeningHours())
				.userEntities(convert(source.getUserList()))
				.build();
	}

	private List<UserEntity> convert(List<User> source){
		return source.stream().map(userEntity -> userToUserEntityConverter.convert(userEntity)).collect(Collectors.toList());
	}

}
