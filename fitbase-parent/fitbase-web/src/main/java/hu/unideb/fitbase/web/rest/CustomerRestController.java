package hu.unideb.fitbase.web.rest;

import hu.unideb.fitbase.commons.pojo.exceptions.BaseException;
import hu.unideb.fitbase.commons.pojo.exceptions.ViolationException;
import hu.unideb.fitbase.commons.pojo.request.CustomerHistoryRequest;
import hu.unideb.fitbase.commons.pojo.request.CustomerRequest;
import hu.unideb.fitbase.commons.pojo.response.CustomerListResponse;
import hu.unideb.fitbase.commons.pojo.response.CustomerSuccessCreateResponse;
import hu.unideb.fitbase.commons.pojo.response.CustomerSuccessUpdateResponse;
import hu.unideb.fitbase.commons.pojo.response.SuccesResponse;
import hu.unideb.fitbase.persistence.entity.CustomerEntity;
import hu.unideb.fitbase.service.api.domain.Customer;
import hu.unideb.fitbase.service.api.domain.CustomerHistory;
import hu.unideb.fitbase.service.api.domain.Gym;
import hu.unideb.fitbase.service.api.domain.Pass;
import hu.unideb.fitbase.service.api.exception.ServiceException;
import hu.unideb.fitbase.service.api.service.CustomerHistoryService;
import hu.unideb.fitbase.service.api.service.CustomerService;
import hu.unideb.fitbase.service.api.service.GymService;
import hu.unideb.fitbase.service.api.service.PassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static hu.unideb.fitbase.commons.path.container.PathContainer.*;
import static hu.unideb.fitbase.commons.path.customer.CustomerPath.CUSTOMERS;
import static hu.unideb.fitbase.commons.path.gym.GymPath.GYMS;
import static hu.unideb.fitbase.commons.path.pass.PassPath.PASSES;

@RestController
public class CustomerRestController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private PassService passService;

    @Autowired
    private GymService gymService;

    @Autowired
    private CustomerHistoryService customerHistoryService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping(path = CUSTOMERS)
    public ResponseEntity postCustomer(@RequestBody CustomerRequest customerRequest)
            throws BaseException {
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
    public ResponseEntity putCustomer(@RequestBody CustomerRequest customerRequest,
                                         @PathVariable(PARAM_CUST_ID) Long custId) throws BaseException {
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
    public ResponseEntity deleteCustomer(@PathVariable(PARAM_CUST_ID) Long custId) throws BaseException {
        customerService.deleteCustomer(custId);
        return ResponseEntity.accepted().body(null);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(path = CUSTOMERS)
    public ResponseEntity getAllCustomers() throws ViolationException {
        List<Customer> getCustomers = customerService.findAllCustomer();
        return ResponseEntity.accepted().body(new CustomerListResponse(getCustomers));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(path = CUSTOMERS + CUST_ID)
    public ResponseEntity showCustomer(@PathVariable(PARAM_CUST_ID) Long custId) throws BaseException {
        Customer customer = customerService.findCustomerById(custId);
        return ResponseEntity.accepted().body(new CustomerListResponse(customer));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(path = CUSTOMERS + EMAILS)
    public ResponseEntity getAllCustomersEmail(){
        List<String> emails = customerService.allCustomersEmail();
        return ResponseEntity.accepted().body(emails);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(path = CUSTOMERS + CUST_ID + PASSES)
    public ResponseEntity addPassToCustomer(@RequestBody CustomerHistoryRequest customerHistoryRequest, @PathVariable(PARAM_CUST_ID) Long custId) throws BaseException {

        Pass findedPass = passService.findPassById(customerHistoryRequest.getPassId());
        Customer findedCustomer = customerService.findCustomerById(custId);
        Gym findedGym = gymService.findGymById(customerHistoryRequest.getGymId());

        CustomerHistory customerHistory = CustomerHistory.builder()
                .passStartDate(customerHistoryRequest.getStartDate())
                .passEndDate(new Date(1995,01,10))
                .passBuyDate(new Date(1995,01,10))
                .status(false)
                .passName(findedPass.getName())
                .passType(findedPass.getPassType())
                .passPrice(findedPass.getPrice())
                .customer(findedCustomer)
                .gym(findedGym)
                .build();

        CustomerHistory customerHistory1 = customerHistoryService.addCustomerHistory(customerHistory);

        return ResponseEntity.accepted().body(new SuccesResponse(customerHistory1, null));
    }
}
