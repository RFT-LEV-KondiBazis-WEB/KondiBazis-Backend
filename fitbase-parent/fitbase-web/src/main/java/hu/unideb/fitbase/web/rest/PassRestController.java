package hu.unideb.fitbase.web.rest;

import hu.unideb.fitbase.commons.pojo.exceptions.ViolationException;
import hu.unideb.fitbase.commons.pojo.request.PassCreateRequest;
import hu.unideb.fitbase.commons.pojo.response.SuccesResponse;
import hu.unideb.fitbase.service.api.domain.Gym;
import hu.unideb.fitbase.service.api.domain.Pass;
import hu.unideb.fitbase.service.api.exception.ServiceException;
import hu.unideb.fitbase.service.api.service.GymService;
import hu.unideb.fitbase.service.api.service.PassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

import static hu.unideb.fitbase.commons.path.container.PathContainer.*;
import static hu.unideb.fitbase.commons.path.pass.PassPath.*;

@RestController
public class PassRestController {

    @Autowired
    private PassService passService;

    @Autowired
    private GymService gymService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = PASS_CREATE + GYM_ID, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createPass(@RequestBody PassCreateRequest passCreateRequest, @PathVariable(PARAM_GYM_ID) Long gymId) throws ViolationException {


        Gym gym = gymService.findById(gymId);

        Pass createPass = createPass(passCreateRequest, gym);
        ResponseEntity result;
        try {
            Pass createdPass = passService.addPass(createPass);
            result = ResponseEntity.accepted().body(new SuccesResponse(createdPass, null));
        } catch (ServiceException e) {
            result = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("FAIL");
        }

        return result;
    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = PASS_INFO + PASS_ID)
    public ResponseEntity getPass(@PathVariable(PARAM_PASS_ID) Long passId) {

        Pass getPass = passService.findPassById(passId);

        return ResponseEntity.accepted().body(new SuccesResponse(getPass, null));

    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping(value = PASS_UPDATE + PASS_ID, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> modificationPass(@RequestBody PassCreateRequest passCreateRequest, @PathVariable(PARAM_PASS_ID) Long passId) throws ViolationException {
        Pass passById = passService.findPassById(passId);

        //TODO
        passService.update(passById);
        return ResponseEntity.ok().body("Módosítva");
    }


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = PASS_DELETE + PASS_ID)
    public ResponseEntity deletePass(@PathVariable(PARAM_PASS_ID) Long passId) throws ViolationException {
        passService.deletePass(passId);
        return ResponseEntity.accepted().body("Megy");
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = PASS_INFO + GYM_ID)
    public ResponseEntity<?> passListGetByGymId(@PathVariable(PARAM_GYM_ID) Long gymId) throws ViolationException {
        List<Pass> byGymIdAllPasses = passService.findByGymIdAllPasses(gymId);
        return ResponseEntity.accepted().body(byGymIdAllPasses);
    }

    private Pass createPass(PassCreateRequest passCreateRequest, Gym gym) {
        return Pass.builder()
                .name(passCreateRequest.getName())
                .price(passCreateRequest.getPrice())
                .passType(passCreateRequest.getPassType())
                .duration(passCreateRequest.getDuration())
                .timeDuration(passCreateRequest.getTimeDuration())
                .passTimeDurationType(passCreateRequest.getPassTimeDurationType())
                .available(passCreateRequest.getAvailable())
                .gymList(Arrays.asList(gym))
                .build();
    }

}
