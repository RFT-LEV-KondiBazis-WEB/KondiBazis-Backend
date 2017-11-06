package hu.unideb.fitbase.service.api.service;

import hu.unideb.fitbase.commons.pojo.exceptions.ViolationException;
import hu.unideb.fitbase.commons.pojo.request.RegistrationRequest;
import hu.unideb.fitbase.commons.pojo.response.Data;
import hu.unideb.fitbase.service.api.exception.ServiceException;

public interface RegistrationService {

    /**
     * Register new user by registration request.
     *
     * @param registrationRequest registration request object.
     * @return registered user object.
     */
    Data register(RegistrationRequest registrationRequest) throws ViolationException, ServiceException;
}
