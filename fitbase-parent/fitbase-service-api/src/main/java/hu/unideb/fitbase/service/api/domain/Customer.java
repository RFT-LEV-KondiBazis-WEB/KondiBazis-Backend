package hu.unideb.fitbase.service.api.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import hu.unideb.fitbase.commons.pojo.enumeration.Gender;
import hu.unideb.fitbase.persistence.entity.PassEntity;
import lombok.Builder;
import lombok.Data;


/**
 * Customer domain class.
 */

@Data
@Builder
public class Customer implements Serializable{

	private static final long serialVersionUID = -462071204360318048L;

	private Long id;
	
	private String email;
	
	private String firstName;
	
	private String lastName;
	
	private String phoneNumber;
	
	private Date birthdayDate;
	
	private Gender gender;
	
	@JsonIgnore
	private List<Gym> gymsList;
	
	private PassEntity passEntity;
}
