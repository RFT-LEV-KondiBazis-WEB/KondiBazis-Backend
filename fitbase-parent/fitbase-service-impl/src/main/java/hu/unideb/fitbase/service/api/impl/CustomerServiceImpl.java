package hu.unideb.fitbase.service.api.impl;

import hu.unideb.fitbase.commons.pojo.exceptions.BaseException;
import hu.unideb.fitbase.persistence.entity.CustomerEntity;
import hu.unideb.fitbase.persistence.repository.CustomerRepository;
import hu.unideb.fitbase.service.api.converter.CustomerEntityToCustomerListConverter;
import hu.unideb.fitbase.service.api.domain.Customer;
import hu.unideb.fitbase.service.api.exception.EntityNotFoundException;
import hu.unideb.fitbase.service.api.exception.ServiceException;
import hu.unideb.fitbase.service.api.service.CustomerService;
import hu.unideb.fitbase.service.api.validator.CustomerValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ConversionService conversionService;

	@Autowired
	private CustomerValidator customerValidator;

	@Autowired
	private CustomerEntityToCustomerListConverter customterEntityToCustomerListConverter;

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Customer addCustomer(Customer customer) throws BaseException {
		customerValidator.validate(customer);
		log.trace(">> save: [customer:{}]", customer);
		Customer convert = conversionService.convert(
				customerRepository.save(conversionService.convert(customer, CustomerEntity.class)), Customer.class);
		log.trace("<< save: [customer:{}]", customer);
		return convert;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Customer updateCustomer(Customer customer) throws BaseException {
		customerValidator.validate(customer);
		log.trace(">> update: [customer:{}]", customer);
		Customer convert = conversionService.convert(
				customerRepository.save(conversionService.convert(customer, CustomerEntity.class)), Customer.class);
		log.trace("<< update: [customer:{}]", customer);
		return convert;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void deleteCustomer(Long id) throws BaseException {
		log.trace(">> deleteCustomer: [id:{}]", id);
		if (Objects.isNull(id)) {
			throw new ServiceException("id is NULL");
		}
		CustomerEntity customerEntity;
		try {
			customerEntity = customerRepository.findById(id);
		} catch (Exception e) {
			String errorMsg = String.format("Error on finding customer by id:%d.", id);
			throw new ServiceException(errorMsg, e);
		}
		if (Objects.isNull(customerEntity)) {
			String errorMsg = String.format("Customer with id:%d not found.", id);
			throw new EntityNotFoundException(errorMsg);
		} else {
			log.trace("<< deleteCustomer: [id:{}]", id);
			customerRepository.delete(id);
		}
	}

	@Override
	public Customer findCustomerByEmail(String email) throws BaseException{
		log.trace(">> findCustomerByEmail: [email:{}]", email);
		if (Objects.isNull(email)) {
			throw new ServiceException("email is NULL");
		}
		CustomerEntity customerEntity;
		try {
			customerEntity = customerRepository.findByEmail(email);
		} catch (Exception e) {
			String errorMsg = String.format("Error on finding customer by email:%s.", email);
			throw new ServiceException(errorMsg, e);
		}
		Customer result = conversionService.convert(customerEntity, Customer.class);
		log.trace("<< findCustomerByEmail: [customer:{}]", result);
		return result;
	}

	@Override
	public Customer findCustomerById(Long id) throws BaseException {
		log.trace(">> findCustomerById: [id:{}]", id);
		if (Objects.isNull(id)) {
			throw new ServiceException("id is NULL");
		}
		CustomerEntity customerEntity;
		try {
			customerEntity = customerRepository.findById(id);
		} catch (Exception e) {
			String errorMsg = String.format("Error on finding customer by id:%d.", id);
			throw new ServiceException(errorMsg, e);
		}
		if (Objects.isNull(customerEntity)) {
			String errorMsg = String.format("Customer with id:%d not found.", id);
			throw new EntityNotFoundException(errorMsg);
		}
		Customer result = conversionService.convert(customerEntity, Customer.class);
		log.trace("<< findCustomerById: [id:{}]", id);
		return result;
	}

	@Override
	public List<Customer> findAllCustomer() {
		List<CustomerEntity> findAllCustomers = customerRepository.findAll();
		return customterEntityToCustomerListConverter.convert(findAllCustomers);
	}

	@Override
	public List<String> allCustomersEmail(){
		List<String> findAllCustomerEmail = customerRepository.getAllEmail();
		return findAllCustomerEmail;
	}

	@Override
	public Long countCustomers() {
		Long countAllCustomer = customerRepository.countCustomers(); 
		return countAllCustomer;
	}
	
	

}
