package hu.unideb.fitbase.web.rest;

import hu.unideb.fitbase.commons.pojo.exceptions.ViolationException;
import hu.unideb.fitbase.commons.pojo.request.ManagerRegistrationRequest;
import hu.unideb.fitbase.commons.pojo.response.MetaResponse;
import hu.unideb.fitbase.commons.pojo.response.RegistrationSuccesResponse;
import hu.unideb.fitbase.service.api.domain.Gym;
import hu.unideb.fitbase.service.api.domain.User;
import hu.unideb.fitbase.service.api.exception.ServiceException;
import hu.unideb.fitbase.service.api.service.GymService;
import hu.unideb.fitbase.service.api.service.RegistrationService;
import hu.unideb.fitbase.web.token.generator.JwtTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static hu.unideb.fitbase.commons.path.gym.ManagerPath.*;

@RestController
public class ManagerCreateRestController {

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private GymService gymService;

    @Autowired
    private JwtTokenGenerator jwtTokenGenerator;

    @RequestMapping(value = GYM_MANAGER_CREATE_URL , method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity registration(@RequestBody ManagerRegistrationRequest request, @PathVariable(PARAM_GYM_ID) Long gymId) throws ViolationException {
        ResponseEntity result;
        try {
            User user = registrationService.addManager(request);

            Gym gym = gymService.findById(gymId);

            gym.getUserList().add(user);

            gymService.updateGym(gym);

            result = ResponseEntity.accepted().body(new RegistrationSuccesResponse(user, new MetaResponse(jwtTokenGenerator.generateToken(request.getUsername()))));
        } catch (ServiceException e) {
            result = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
        return result;
    }
}