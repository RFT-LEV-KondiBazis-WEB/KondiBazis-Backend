package hu.unideb.fitbase.service.api.service;

import java.util.List;

import hu.unideb.fitbase.commons.pojo.exceptions.ViolationException;
import hu.unideb.fitbase.service.api.domain.Customer;
import hu.unideb.fitbase.service.api.exception.ServiceException;

public interface CustomerService {

	Customer addCustomer(Customer customer) throws ViolationException, ServiceException;
	
	Customer updateCustomer(Customer customer) throws ViolationException;
	
	void deleteCustomer(Customer customer) throws ViolationException;
	
	Customer findByEmail(String email);
	
	Customer findById(Long id);
	
	List<Customer> findAll(Customer customer);
	
	
}
