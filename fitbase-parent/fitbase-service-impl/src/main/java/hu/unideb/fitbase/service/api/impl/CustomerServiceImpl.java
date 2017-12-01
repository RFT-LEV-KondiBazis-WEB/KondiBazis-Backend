package hu.unideb.fitbase.service.api.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.unideb.fitbase.commons.pojo.exceptions.ViolationException;
import hu.unideb.fitbase.persistence.entity.CustomerEntity;
import hu.unideb.fitbase.persistence.repository.CustomerRepository;
import hu.unideb.fitbase.service.api.converter.CustomerEntityToCustomerListConverter;
import hu.unideb.fitbase.service.api.domain.Customer;
import hu.unideb.fitbase.service.api.exception.ServiceException;
import hu.unideb.fitbase.service.api.service.CustomerService;
import hu.unideb.fitbase.service.api.validator.AbstractValidator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ConversionService conversionService;

	@Autowired
	private AbstractValidator<Customer> customerValidator;

	@Autowired
	private CustomerEntityToCustomerListConverter customterEntityToCustomerListConverter;

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Customer addCustomer(Customer customer) throws ViolationException, ServiceException {
		customerValidator.validate(customer);
		log.trace(">> save: [customer:{}]", customer);
		Customer convert = conversionService.convert(
				customerRepository.save(conversionService.convert(customer, CustomerEntity.class)), Customer.class);
		log.trace("<< save: [customer:{}]", customer);
		return convert;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Customer updateCustomer(Customer customer) throws ViolationException {
		customerValidator.validate(customer);
		log.trace(">> update: [customer:{}]", customer);
		Customer convert = conversionService.convert(
				customerRepository.save(conversionService.convert(customer, CustomerEntity.class)), Customer.class);
		log.trace("<< update: [customer:{}]", customer);
		return convert;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void deleteCustomer(Customer customer) throws ViolationException {
		log.trace(">> delete: [customer:{}]", customer);
		customerRepository.delete(conversionService.convert(customer, CustomerEntity.class));
		log.trace("<< delete: [customer:{}]", customer);
	}

	@Override
	public Customer findByEmail(String email) {
		log.trace(">> findByEmail: [email:{}]", email);
		CustomerEntity customerEntity = customerRepository.findByEmail(email);
		Customer convert = conversionService.convert(customerEntity, Customer.class);
		log.trace("<< findByEmail: [email:{}]", email);
		return convert;
	}

	@Override
	public Customer findById(Long id) {
		log.trace(">> findById: [id:{}]", id);
		CustomerEntity customerEntity = customerRepository.findById(id);
		Customer convert = conversionService.convert(customerEntity, Customer.class);
		log.trace("<< findById: [id:{}]", id);
		return convert;
	}

	@Override
	public List<Customer> findAll(Customer customer) {
		List<CustomerEntity> findAllCustomers = customerRepository.findAll();
		return customterEntityToCustomerListConverter.convert(findAllCustomers);
	}

}
