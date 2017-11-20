package hu.unideb.fitbase.web.rest;

import hu.unideb.fitbase.commons.pojo.exceptions.ViolationException;
import hu.unideb.fitbase.commons.pojo.request.ManagerRegistrationRequest;
import hu.unideb.fitbase.commons.pojo.request.RegistrationRequest;
import hu.unideb.fitbase.commons.pojo.response.RegistrationResponse;
import hu.unideb.fitbase.service.api.exception.ServiceException;
import hu.unideb.fitbase.service.api.service.RegistrationService;
import hu.unideb.fitbase.web.token.generator.JwtTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static hu.unideb.fitbase.commons.path.gym.ManagerPath.GYM_MANAGER_CREATE_URL;

@RestController
public class ManagerCreateRestController {

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private JwtTokenGenerator jwtTokenGenerator;

    @RequestMapping(value = GYM_MANAGER_CREATE_URL, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<RegistrationResponse> registration(@RequestBody ManagerRegistrationRequest request) throws ViolationException {
        ResponseEntity result = null;
        try {
            registrationService.addManager(request);
            result = ResponseEntity.accepted().body(jwtTokenGenerator.generateToken(request.getUsername()));
        } catch (ServiceException e) {
            result = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
        return result;
    }
}