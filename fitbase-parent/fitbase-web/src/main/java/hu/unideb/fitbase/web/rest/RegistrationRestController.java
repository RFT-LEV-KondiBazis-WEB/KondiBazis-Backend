package hu.unideb.fitbase.web.rest;

import hu.unideb.fitbase.commons.pojo.exceptions.ViolationException;
import hu.unideb.fitbase.commons.pojo.request.RegistrationRequest;
import hu.unideb.fitbase.commons.pojo.response.RegistrationResponse;
import hu.unideb.fitbase.service.api.exception.ServiceException;
import hu.unideb.fitbase.service.api.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

import static hu.unideb.fitbase.commons.constants.RegistrationConstants.REGISTRATION_WAS_SUCCESSFUL;
import static hu.unideb.fitbase.commons.path.registration.RegistrationPath.REGISTARATION_URL;

@RestController
public class RegistrationRestController {

    @Autowired
    private RegistrationService registrationService;

    @RequestMapping(value = REGISTARATION_URL, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegistrationResponse> registration(@RequestBody RegistrationRequest request) throws ViolationException {
        ResponseEntity result = null;
        try {
            registrationService.register(request);
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
