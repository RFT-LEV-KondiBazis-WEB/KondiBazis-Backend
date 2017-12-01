package hu.unideb.fitbase.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.http.HeadersBeanDefinitionParser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import hu.unideb.fitbase.commons.pojo.exceptions.ViolationException;
import hu.unideb.fitbase.commons.pojo.request.CustomerRequest;
import hu.unideb.fitbase.commons.pojo.response.CustomerSuccessCreateResponse;
import hu.unideb.fitbase.service.api.domain.Customer;
import hu.unideb.fitbase.service.api.exception.ServiceException;
import hu.unideb.fitbase.service.api.service.CustomerService;

import static hu.unideb.fitbase.commons.path.customer.CustomerPath.CUST_CREATE_URL;
import static hu.unideb.fitbase.commons.path.customer.CustomerPath.CUST_UPDATE_URL;
import static hu.unideb.fitbase.commons.path.customer.CustomerPath.CUST_DELETE_URL;
import static hu.unideb.fitbase.commons.path.customer.CustomerPath.CUST_LIST_BY_URL;
import static hu.unideb.fitbase.commons.path.customer.CustomerPath.CUST_ID;
import static hu.unideb.fitbase.commons.path.customer.CustomerPath.PARAM_CUST_ID;

import javax.servlet.http.HttpServletRequest;

public class CustomerRestController {
	
	@Autowired
	private CustomerService customerService;
	
	@Value("${jwt.header}")
	private String tokenHeader;

	@PreAuthorize("isAuthenticated()")
	@PostMapping(path = CUST_CREATE_URL)
	public ResponseEntity<?> postCustomer(@RequestBody CustomerRequest customerRequest, HttpServletRequest request) throws ViolationException {
		
		Customer customer = Customer.builder()
				.email(customerRequest.getEmail())
				.firstName(customerRequest.getFirstName())
				.lastName(customerRequest.getLastName())
				.phoneNumber(customerRequest.getPhoneNumber())
				.birthdayDate(customerRequest.getBirthdayDate())
				.gender(customerRequest.getGender())
				.build();
		ResponseEntity<?> result = null;
		try {
			customerService.addCustomer(customer);
			result = ResponseEntity.accepted().body(new CustomerSuccessCreateResponse(customer));
		} catch (ServiceException e) {
			result = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("FAIL");
		}
		return result;
	}
}
