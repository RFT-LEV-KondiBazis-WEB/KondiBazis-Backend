package hu.unideb.fitbase.service.api.impl;

import hu.unideb.fitbase.commons.pojo.exceptions.ViolationException;
import hu.unideb.fitbase.commons.pojo.request.RegistrationRequest;
import hu.unideb.fitbase.commons.pojo.response.Data;
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

    @Override
    public Data register(RegistrationRequest registrationRequest) throws ViolationException, ServiceException {
        Objects.requireNonNull(registrationRequest, REGISTRATION_REQUEST_CAN_NOT_BE_NULL);
        log.info("Registering new user with username:{}", registrationRequest.getUsername());
        registrationRequestValidator.validate(registrationRequest);
        Data convertedUser = conversionService.convert(registrationRequest, Data.class);
        Data savedUser = userService.save(convertedUser);
        log.info("Registration successfully finished.");
        return savedUser;
    }
}
