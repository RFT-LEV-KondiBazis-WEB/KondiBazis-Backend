package hu.unideb.fitbase.service.api.impl;

import hu.unideb.fitbase.commons.pojo.exceptions.BaseException;
import hu.unideb.fitbase.commons.pojo.exceptions.ViolationException;
import hu.unideb.fitbase.service.api.domain.User;
import hu.unideb.fitbase.service.api.domain.UserModification;
import hu.unideb.fitbase.service.api.exception.ServiceException;
import hu.unideb.fitbase.service.api.service.UserModificationService;
import hu.unideb.fitbase.service.api.service.UserService;
import hu.unideb.fitbase.service.api.validator.AbstractValidator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class UserModificationServiceImpl implements UserModificationService {

    private static final String MODIFICATION_IS_NULL = "Modification is NULL";

    private static final BCryptPasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    @Autowired
    private AbstractValidator<UserModification> userModificationAbstractValidator;

    @Autowired
    private UserService userService;

    @Override
    public User modifyUser(UserModification userModification) throws BaseException {
        Objects.requireNonNull(userModification, MODIFICATION_IS_NULL);
        userModificationAbstractValidator.validate(userModification);
        User user = userService.findById(userModification.getId());
        log.info("Modifying user with username:{}", user.getUsername());
        if (StringUtils.isNotBlank(userModification.getPassword())) {
            user.setPassword(PASSWORD_ENCODER.encode(userModification.getPassword()));
        }
        if(Objects.nonNull(userModification.getEmail())){
            user.setEmail(userModification.getEmail());
        }
        if(Objects.nonNull(userModification.getFirstName())){
            user.setFirstName(userModification.getFirstName());
        }
        if(Objects.nonNull(userModification.getLastName())){
            user.setLastName(userModification.getLastName());
        }
        User savedUser = userService.save(user);
        log.info("User modification done.");
        return savedUser;
    }
}
