package hu.unideb.fitbase.service.api.impl;

import hu.unideb.fitbase.commons.pojo.exceptions.ViolationException;
import hu.unideb.fitbase.commons.pojo.request.ManagerRegistrationRequest;
import hu.unideb.fitbase.commons.pojo.request.RegistrationRequest;
import hu.unideb.fitbase.service.api.domain.User;
import hu.unideb.fitbase.service.api.exception.ServiceException;
import hu.unideb.fitbase.service.api.service.RegistrationService;
import hu.unideb.fitbase.service.api.service.UserService;
import hu.unideb.fitbase.service.api.validator.AbstractValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
public class RegistrationServiceImpl implements RegistrationService {

    private static final String REGISTRATION_REQUEST_CAN_NOT_BE_NULL = "Registration request can not be NULL.";

    @Autowired
    private UserService userService;

    @Autowired
    private ConversionService conversionService;

    @Autowired
    private AbstractValidator<RegistrationRequest> registrationRequestValidator;

    @Autowired
    private AbstractValidator<ManagerRegistrationRequest> managerRegistrationRequestAbstractValidator;

    @Override
    public User register(RegistrationRequest registrationRequest) throws ViolationException, ServiceException {
        Objects.requireNonNull(registrationRequest, REGISTRATION_REQUEST_CAN_NOT_BE_NULL);
        log.info("Registering new user with username:{}", registrationRequest.getUsername());
        registrationRequestValidator.validate(registrationRequest);
        User convertedUser = conversionService.convert(registrationRequest, User.class);
        User savedUser = userService.save(convertedUser);
        log.info("Registration successfully finished.");
        return savedUser;
    }

    @Override
    public User addManager(ManagerRegistrationRequest managerRegistrationRequest) throws ViolationException, ServiceException {
        Objects.requireNonNull(managerRegistrationRequest, REGISTRATION_REQUEST_CAN_NOT_BE_NULL);
        log.info("Registering new manager with username:{}", managerRegistrationRequest.getUsername());
        managerRegistrationRequestAbstractValidator.validate(managerRegistrationRequest);
        User convertedUser = conversionService.convert(managerRegistrationRequest, User.class);
        User savedUser = userService.save(convertedUser);
        log.info("Registration successfully finished.");
        return savedUser;
    }
}
