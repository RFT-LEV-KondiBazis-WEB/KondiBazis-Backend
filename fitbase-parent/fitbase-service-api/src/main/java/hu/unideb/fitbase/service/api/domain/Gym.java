package hu.unideb.fitbase.service.api.domain;

import java.util.List;

import hu.unideb.fitbase.persistence.entity.CustomerEntity;
import hu.unideb.fitbase.persistence.entity.UserEntity;
import lombok.Builder;
import lombok.Data;

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
}
