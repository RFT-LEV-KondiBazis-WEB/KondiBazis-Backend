package hu.unideb.fitbase.service.api.domain;

import hu.unideb.fitbase.persistence.entity.UserEntity;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Gym domain class.
 */

@Data
@Builder
public class Gym {

	private Long id;
	
	private String name;
	
	private String city;
	
	private String address;
	
	private Integer zipCode;
	
	private String description;
	
	private String openingHours;

	private List<User> userList;
}
