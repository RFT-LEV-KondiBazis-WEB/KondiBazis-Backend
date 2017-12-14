package hu.unideb.fitbase.service.api.service;

import java.util.List;

import hu.unideb.fitbase.commons.pojo.exceptions.BaseException;
import hu.unideb.fitbase.commons.pojo.exceptions.ViolationException;
import hu.unideb.fitbase.service.api.domain.Customer;
import hu.unideb.fitbase.service.api.exception.EntityNotFoundException;
import hu.unideb.fitbase.service.api.exception.ServiceException;

public interface CustomerService {

	Customer addCustomer(Customer customer) throws BaseException;
	
	Customer updateCustomer(Customer customer) throws BaseException;
	
	void deleteCustomer(Long id) throws BaseException;
	
	Customer findCustomerByEmail(String email) throws BaseException;
	
	Customer findCustomerById(Long id) throws BaseException;
	
	List<Customer> findAllCustomer();

	List<String> allCustomersEmail();
	
	Long countCustomers();
}
