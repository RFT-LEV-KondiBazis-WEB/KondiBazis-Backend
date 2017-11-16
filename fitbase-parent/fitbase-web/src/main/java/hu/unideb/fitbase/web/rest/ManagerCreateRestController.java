package hu.unideb.fitbase.web.rest;

import hu.unideb.fitbase.commons.pojo.exceptions.ViolationException;
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

@CrossOrigin(maxAge = 3600)
@RestController
public class ManagerCreateRestController {

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private JwtTokenGenerator jwtTokenGenerator;

    @CrossOrigin(origins = "http://localhost:8081")
    @RequestMapping(value = GYM_MANAGER_CREATE_URL, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<RegistrationResponse> registration(@RequestBody RegistrationRequest request) throws ViolationException {
        ResponseEntity result = null;
        try {
            registrationService.register(request);
            result = ResponseEntity.accepted().body(jwtTokenGenerator.generateToken(request.getUsername()));
//            RegistrationResponse response = new RegistrationResponse(Collections.emptyList());
//            result = ResponseEntity.accepted()
//                    .body(response);
        } catch (ServiceException e) {
            result = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
        return result;
    }
}