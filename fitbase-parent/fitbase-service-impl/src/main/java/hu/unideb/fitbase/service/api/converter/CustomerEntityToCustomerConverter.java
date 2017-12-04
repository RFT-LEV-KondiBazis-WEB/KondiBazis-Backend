package hu.unideb.fitbase.service.api.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import hu.unideb.fitbase.persistence.entity.CustomerEntity;
import hu.unideb.fitbase.service.api.domain.Customer;

@Component
public class CustomerEntityToCustomerConverter implements Converter<CustomerEntity, Customer>{
	
	@Override
	public Customer convert(CustomerEntity source) {
		return Customer.builder()
				.id(source.getId())
				.email(source.getEmail())
				.firstName(source.getFirstName())
				.lastName(source.getLastName())
				.phoneNumber(source.getPhoneNumber())
				.birthdayDate(source.getBirthdayDate())
				.gender(source.getGender())
				.build();
	}

}
