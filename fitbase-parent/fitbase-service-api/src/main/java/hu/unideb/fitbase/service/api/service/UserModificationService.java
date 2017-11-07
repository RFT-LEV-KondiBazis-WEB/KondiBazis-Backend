package hu.unideb.fitbase.service.api.service;

import hu.unideb.fitbase.commons.pojo.exceptions.ViolationException;
import hu.unideb.fitbase.service.api.domain.User;
import hu.unideb.fitbase.service.api.domain.UserModification;
import hu.unideb.fitbase.service.api.exception.ServiceException;
import org.springframework.stereotype.Service;

public interface UserModificationService {
    /**
     * Modify user by request.
     *
     * @param userModificationRequest user modification request.
     * @return modified user.
     * @throws ServiceException when an exception occurs.
     */
    User modifyUser(UserModification userModificationRequest) throws ServiceException, ViolationException;

}
