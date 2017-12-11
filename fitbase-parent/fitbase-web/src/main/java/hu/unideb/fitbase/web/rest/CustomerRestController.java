package hu.unideb.fitbase.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hu.unideb.fitbase.commons.pojo.exceptions.ViolationException;
import hu.unideb.fitbase.commons.pojo.request.CustomerRequest;
import hu.unideb.fitbase.commons.pojo.response.CustomerListResponse;
import hu.unideb.fitbase.commons.pojo.response.CustomerSuccessCreateResponse;
import hu.unideb.fitbase.commons.pojo.response.CustomerSuccessUpdateResponse;
import hu.unideb.fitbase.service.api.domain.Customer;
import hu.unideb.fitbase.service.api.exception.ServiceException;
import hu.unideb.fitbase.service.api.service.CustomerService;

import static hu.unideb.fitbase.commons.path.customer.CustomerPath.CUSTOMERS;
import static hu.unideb.fitbase.commons.path.customer.CustomerPath.PARAM_CUST_ID;
import static hu.unideb.fitbase.commons.path.customer.CustomerPath.CUST_ID;

import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

@RestController
public class CustomerRestController {

	@Autowired
	private CustomerService customerService;

	@Value("${jwt.header}")
	private String tokenHeader;

	@PreAuthorize("isAuthenticated()")
	@PostMapping(path = CUSTOMERS)
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
			customer = customerService.addCustomer(customer);
			result = ResponseEntity.accepted().body(new CustomerSuccessCreateResponse(customer));
		} catch (ServiceException e) {
			result = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("FAIL");
		}
		return result;
	}

	@PreAuthorize("isAuthenticated()")
	@PutMapping(path = CUSTOMERS + CUST_ID)
	public ResponseEntity<?> putCustomer(@RequestBody CustomerRequest customerRequest,
			@PathVariable(PARAM_CUST_ID) Long custId) throws ViolationException {
		if (Objects.isNull(customerRequest)) {
			return ResponseEntity.badRequest().body("null");
		}
		Customer customer = Customer.builder().id(custId).email(customerRequest.getEmail())
				.firstName(customerRequest.getFirstName()).lastName(customerRequest.getLastName())
				.phoneNumber(customerRequest.getPhoneNumber()).birthdayDate(customerRequest.getBirthdayDate())
				.gender(customerRequest.getGender()).build();

		customerService.updateCustomer(customer);
		return ResponseEntity.accepted().body(new CustomerSuccessUpdateResponse(customer));
	}

	@PreAuthorize("isAuthenticated()")
	@DeleteMapping(path = CUSTOMERS + CUST_ID)
	public ResponseEntity<?> deleteCustomer(@PathVariable(PARAM_CUST_ID) Long custId) throws ViolationException {
		Customer customer = customerService.findById(custId);
		customerService.deleteCustomer(customer);
		return ResponseEntity.accepted().body(null);
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping(path = CUSTOMERS)
	public ResponseEntity<?> getAllCustomers() throws ViolationException {
		List<Customer> getCustomers = customerService.findAll();
		return ResponseEntity.accepted().body(new CustomerListResponse(getCustomers));
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping(path = CUSTOMERS + CUST_ID)
	public ResponseEntity<?> showCustomer(@PathVariable(PARAM_CUST_ID) Long custId) throws ViolationException {
		Customer customer = customerService.findById(custId);
		return ResponseEntity.accepted().body(new CustomerListResponse(customer));
	}
}
