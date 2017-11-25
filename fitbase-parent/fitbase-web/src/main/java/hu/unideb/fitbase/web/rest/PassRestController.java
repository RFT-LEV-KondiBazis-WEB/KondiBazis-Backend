package hu.unideb.fitbase.web.rest;

import hu.unideb.fitbase.commons.pojo.exceptions.ViolationException;
import hu.unideb.fitbase.commons.pojo.request.PassCreateRequest;
import hu.unideb.fitbase.commons.pojo.response.PassCreateSuccesResponse;
import hu.unideb.fitbase.service.api.domain.FitBaseUser;
import hu.unideb.fitbase.service.api.domain.Gym;
import hu.unideb.fitbase.service.api.domain.Pass;
import hu.unideb.fitbase.service.api.domain.User;
import hu.unideb.fitbase.service.api.exception.ServiceException;
import hu.unideb.fitbase.service.api.service.GymService;
import hu.unideb.fitbase.service.api.service.PassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

import static hu.unideb.fitbase.commons.path.pass.PassPath.*;

@RestController
public class PassRestController {

    @Autowired
    private PassService passService;

    @Autowired
    private GymService gymService;

    @RequestMapping(value = PASS_CREATE_URL, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createPass(@RequestBody PassCreateRequest source, @PathVariable(PARAM_GYM_ID) Long gymId) throws ViolationException {
        ResponseEntity result;

        Gym gyms =gymService.findById(gymId);

        try {
            Pass pass = Pass.builder()
                    .name(source.getName())
                    .isLimited(source.getIsLimited())
                    .limitNumber(source.getLimitNumber())
                    .duration(source.getDuration())
                    .price(source.getPrice())
                    .available(source.getAvailable())
                    .gymList(Arrays.asList(gyms))
                    .build();
            passService.addPass(pass);
            result = ResponseEntity.accepted().body(new PassCreateSuccesResponse(pass));
        } catch (ServiceException e) {
            result = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

        return null;
    }

    @RequestMapping(value = PASS_MODIFICATION_URL, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> modificationPass(@RequestBody PassCreateRequest passCreateRequest) throws ViolationException {
        return null;
    }

    private User getUser() {
        return ((FitBaseUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
    }

    @RequestMapping(value = PASS_DELETE_URL, method = RequestMethod.GET)
    public ResponseEntity<?> deletePass(@PathVariable(PARAM_PASS_ID) Long passId) throws ViolationException {
        passService.deletePass(passId);
        return ResponseEntity.accepted().body("Megy");
    }

}
