package hu.unideb.fitbase.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.http.HeadersBeanDefinitionParser;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import hu.unideb.fitbase.commons.pojo.exceptions.ViolationException;
import hu.unideb.fitbase.commons.pojo.request.CustomerRequest;
import hu.unideb.fitbase.commons.pojo.response.CustomerSuccessCreateResponse;
import hu.unideb.fitbase.commons.pojo.response.CustomerSuccessUpdateResponse;
import hu.unideb.fitbase.service.api.domain.Customer;
import hu.unideb.fitbase.service.api.exception.ServiceException;
import hu.unideb.fitbase.service.api.service.CustomerService;

import static hu.unideb.fitbase.commons.path.customer.CustomerPath.CUST_CREATE_URL;
import static hu.unideb.fitbase.commons.path.customer.CustomerPath.CUST_UPDATE_URL;
import static hu.unideb.fitbase.commons.path.customer.CustomerPath.CUST_DELETE_URL;
import static hu.unideb.fitbase.commons.path.customer.CustomerPath.CUST_LIST_BY_URL;
import static hu.unideb.fitbase.commons.path.customer.CustomerPath.CUST_ID;
import static hu.unideb.fitbase.commons.path.customer.CustomerPath.PARAM_CUST_ID;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

public class CustomerRestController {

	@Autowired
	private CustomerService customerService;

	@Value("${jwt.header}")
	private String tokenHeader;

	@PreAuthorize("isAuthenticated()")
	@PostMapping(path = CUST_CREATE_URL)
	public ResponseEntity<?> postCustomer(@RequestBody CustomerRequest customerRequest, HttpServletRequest request)
			throws ViolationException {
		if (Objects.isNull(customerRequest)) {
			return ResponseEntity.badRequest().body("null");
		}
		Customer customer = Customer.builder().email(customerRequest.getEmail())
				.firstName(customerRequest.getFirstName()).lastName(customerRequest.getLastName())
				.phoneNumber(customerRequest.getPhoneNumber()).birthdayDate(customerRequest.getBirthdayDate())
				.gender(customerRequest.getGender()).build();
		ResponseEntity<?> result = null;
		try {
			customerService.addCustomer(customer);
			result = ResponseEntity.accepted().body(new CustomerSuccessCreateResponse(customer));
		} catch (ServiceException e) {
			result = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("FAIL");
		}
		return result;
	}

	@PreAuthorize("isAuthenticated()")
	@PutMapping(path = CUST_UPDATE_URL + CUST_ID)
	public ResponseEntity<?> putCustomer(@RequestBody CustomerRequest customerRequest,
			@PathVariable(PARAM_CUST_ID) Long custId) throws ViolationException {
		if (Objects.isNull(customerRequest)) {
			return ResponseEntity.badRequest().body("null");
		}
		Customer customer = Customer.builder().id(custId).email(customerRequest.getEmail())
				.firstName(customerRequest.getFirstName()).lastName(customerRequest.getLastName())
				.phoneNumber(customerRequest.getPhoneNumber()).birthdayDate(customerRequest.getBirthdayDate())
				.gender(customerRequest.getGender()).build();

		ResponseEntity<?> result = null;
		customerService.updateCustomer(customer);
		result = ResponseEntity.accepted().body(new CustomerSuccessUpdateResponse(customer));
		return result;
	}

	@PreAuthorize("isAuthenticated()")
	@DeleteMapping(path = CUST_DELETE_URL + CUST_ID)
	public ResponseEntity<?> deleteCustomer(@RequestBody CustomerRequest customerRequest,
			@PathVariable(PARAM_CUST_ID) Long custId) throws ViolationException {
		Customer customer = customerService.findById(custId);
		customerService.deleteCustomer(customer);
		return ResponseEntity.accepted().body("Delete Success!");
	}
//TODO
	@PreAuthorize("isAuthenticated()")
	@GetMapping(path = CUST_LIST_BY_URL)
	public ResponseEntity<?> getCustomers() {
		return null;

	}
}
