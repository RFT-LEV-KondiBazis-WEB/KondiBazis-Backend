package hu.unideb.fitbase.web.rest;

import hu.unideb.fitbase.commons.pojo.exceptions.BaseException;
import hu.unideb.fitbase.commons.pojo.request.ManagerRegistrationRequest;
import hu.unideb.fitbase.commons.pojo.request.RegistrationRequest;
import hu.unideb.fitbase.commons.pojo.response.MetaResponse;
import hu.unideb.fitbase.commons.pojo.response.SuccesResponse;
import hu.unideb.fitbase.service.api.domain.Gym;
import hu.unideb.fitbase.service.api.domain.User;
import hu.unideb.fitbase.service.api.exception.ServiceException;
import hu.unideb.fitbase.service.api.service.GymService;
import hu.unideb.fitbase.service.api.service.RegistrationService;
import hu.unideb.fitbase.web.token.generator.JwtTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static hu.unideb.fitbase.commons.path.container.PathContainer.GYM_ID;
import static hu.unideb.fitbase.commons.path.container.PathContainer.PARAM_GYM_ID;
import static hu.unideb.fitbase.commons.path.gym.GymPath.GYMS;
import static hu.unideb.fitbase.commons.path.registration.RegistrationPath.REGISTARATION_URL;
import static hu.unideb.fitbase.commons.path.user.UserPath.MANAGER;

@RestController
public class RegistrationRestController {

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private JwtTokenGenerator jwtTokenGenerator;

    @Autowired
    private GymService gymService;

    @RequestMapping(value = REGISTARATION_URL, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity registration(@RequestBody RegistrationRequest request) throws BaseException {
        ResponseEntity result;
        try {
            registrationService.register(request);
            result = ResponseEntity.accepted().body(new SuccesResponse(null, new MetaResponse(jwtTokenGenerator.generateToken(request.getUsername()))));
        } catch (ServiceException e) {
            result = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return result;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = GYMS + GYM_ID + MANAGER, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity registration(@RequestBody ManagerRegistrationRequest request, @PathVariable(PARAM_GYM_ID) Long gymId) throws BaseException {
        ResponseEntity result;
        try {
            User user = registrationService.addManager(request);

            Gym gym = gymService.findGymById(gymId);

            gym.getUserList().add(user);

            gymService.updateGym(gym);

            result = ResponseEntity.accepted().body(new SuccesResponse(user, new MetaResponse(jwtTokenGenerator.generateToken(request.getUsername()))));
        } catch (ServiceException e) {
            result = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
        return result;
    }
}
