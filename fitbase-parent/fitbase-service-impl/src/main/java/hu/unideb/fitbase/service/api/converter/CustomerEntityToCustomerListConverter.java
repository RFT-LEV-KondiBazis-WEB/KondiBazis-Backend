package hu.unideb.fitbase.service.api.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import hu.unideb.fitbase.persistence.entity.CustomerEntity;
import hu.unideb.fitbase.service.api.domain.Customer;

public class CustomerEntityToCustomerListConverter implements Converter<List<CustomerEntity>, List<Customer>>{

	@Autowired
	private CustomerEntityToCustomerConverter customterEntityToCustomerConverter;
	
	@Override
	public List<Customer> convert(List<CustomerEntity> source) {
		return source.stream().map(customer -> customterEntityToCustomerConverter.convert(customer)).collect(Collectors.toList());
	}

}
