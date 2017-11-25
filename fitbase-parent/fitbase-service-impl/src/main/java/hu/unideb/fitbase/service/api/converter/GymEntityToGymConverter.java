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
public class GymEntityToGymConverter implements Converter<GymEntity, Gym>{

	@Autowired
	UserEntityToUserConverter userEntityToUserConverter;

	@Override
	public Gym convert(GymEntity source) {
		return Gym.builder()
				.id(source.getId())
				.name(source.getName())
				.city(source.getCity())
				.address(source.getAddress())
				.zipCode(String.valueOf(source.getZipCode()))
				.description(source.getDescription())
				.openingHours(source.getOpeningHours())
				.userList(convert(source.getUsers()))
				.build();
	}

	private List<User> convert(List<UserEntity> source){
		return source.stream().map(user -> userEntityToUserConverter.convert(user)).collect(Collectors.toList());
	}

}
