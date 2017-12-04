package hu.unideb.fitbase.web.rest;

import hu.unideb.fitbase.commons.pojo.exceptions.ViolationException;
import hu.unideb.fitbase.commons.pojo.request.PassCreateRequest;
import hu.unideb.fitbase.commons.pojo.response.PassCreateSuccesResponse;
import hu.unideb.fitbase.persistence.entity.GymEntity;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

import static hu.unideb.fitbase.commons.path.pass.PassPath.*;

@RestController
public class PassRestController {

    @Autowired
    private PassService passService;

    @Autowired
    private GymService gymService;

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = PASS_ADMIN_CREATE_URL + GYM_ID, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createPassByAdmin(@RequestBody PassCreateRequest source, @PathVariable(PARAM_GYM_ID) Long gymId) throws ViolationException {
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

    @PreAuthorize("hasRole('MANAGER')")
    @RequestMapping(value = PASS_MANAGER_CREATE_URL + GYM_ID, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createPassByManager(@RequestBody PassCreateRequest source, @PathVariable(PARAM_GYM_ID) Long gymId) throws ViolationException {
        ResponseEntity result;

        Gym gyms =gymService.findById(gymId);
        try {
           Pass pass = createPass(source, gyms);
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

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = PASS_DELETE_URL + PASS_ID)
    public ResponseEntity deletePass(@PathVariable(PARAM_PASS_ID) Long passId) throws ViolationException {
        passService.deletePass(passId);
        return ResponseEntity.accepted().body("Megy");
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = PASSES_LIST_BY_GYM_URL + GYM_ID)
    public ResponseEntity<?> passListGetByGymId(@PathVariable(PARAM_GYM_ID) Long gymId) throws ViolationException {
        List<Pass> byGymIdAllPasses = passService.findByGymIdAllPasses(gymId);
        return ResponseEntity.accepted().body(byGymIdAllPasses);
    }

    private Pass createPass(PassCreateRequest passCreateRequest, Gym gym) {
        return Pass.builder()
                .name(passCreateRequest.getName())
                .isLimited(passCreateRequest.getIsLimited())
                .limitNumber(passCreateRequest.getLimitNumber())
                .duration(passCreateRequest.getDuration())
                .price(passCreateRequest.getPrice())
                .available(passCreateRequest.getAvailable())
                .gymList(Arrays.asList(gym))
                .build();
    }


}
