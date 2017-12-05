package hu.unideb.fitbase.web.rest;

import hu.unideb.fitbase.commons.pojo.exceptions.ViolationException;
import hu.unideb.fitbase.commons.pojo.request.UserModificationRequest;
import hu.unideb.fitbase.commons.pojo.response.SuccesResponse;
import hu.unideb.fitbase.service.api.domain.FitBaseUser;
import hu.unideb.fitbase.service.api.domain.User;
import hu.unideb.fitbase.service.api.domain.UserModification;
import hu.unideb.fitbase.service.api.exception.ServiceException;
import hu.unideb.fitbase.service.api.service.UserModificationService;
import hu.unideb.fitbase.service.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.ValidationException;
import java.util.Objects;

import static hu.unideb.fitbase.commons.path.usermodification.UserModificationPath.USER_MODIFICATION_URL;

@RestController
public class UserModificationRestController {

    @Autowired
    private UserModificationService userModificationService;

    @Autowired
    private UserService userService;

    @PreAuthorize("isAuthenticated()")
    @PutMapping(path = USER_MODIFICATION_URL)
    public ResponseEntity<?> putUserModification(@RequestBody UserModificationRequest userModificationRequest) throws ViolationException{
        if(Objects.isNull(userModificationRequest)){
            return ResponseEntity.badRequest().body("null");
        }
        UserModification userModification = UserModification.builder()
                .id(getUserId())
                .email(userModificationRequest.getEmail())
                .password(userModificationRequest.getPassword())
                .passwordConfirm(userModificationRequest.getPasswordConfirm())
                .firstName(userModificationRequest.getFirstName())
                .lastName(userModificationRequest.getLastName())
                .build();

        ResponseEntity<?> result;
        try {
            userModificationService.modifyUser(userModification);
            result = ResponseEntity.ok().body(new SuccesResponse(userService.findById(getUserId()), null));
        } catch (ServiceException e) {
            result = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
        return result;
    }

    private Long getUserId() {
        return ((FitBaseUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser().getId();
    }

}