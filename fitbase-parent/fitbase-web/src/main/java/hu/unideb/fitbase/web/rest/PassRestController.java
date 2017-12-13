package hu.unideb.fitbase.web.rest;

import hu.unideb.fitbase.commons.pojo.exceptions.BaseException;
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
import java.util.Objects;

import static hu.unideb.fitbase.commons.path.container.PathContainer.*;
import static hu.unideb.fitbase.commons.path.gym.GymPath.GYMS;
import static hu.unideb.fitbase.commons.path.pass.PassPath.PASSES;

@RestController
public class PassRestController {

    @Autowired
    private PassService passService;

    @Autowired
    private GymService gymService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = GYMS + GYM_ID + PASSES, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createPass(@RequestBody PassCreateRequest passCreateRequest, @PathVariable(PARAM_GYM_ID) Long gymId) throws BaseException {
        ResponseEntity result;
        try {
            Pass createdPass = passService.addPass(passCreateRequest);
            Gym gym = gymService.findById(gymId);
            gym.getPasses().add(createdPass);
            gymService.updateGym(gym);

            result = ResponseEntity.accepted().body(new SuccesResponse(createdPass, null));
        } catch (ServiceException e) {
            result = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("FAIL");
        }

        return result;
    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = GYMS + GYM_ID + PASSES + PASS_ID)
    public ResponseEntity getPass(@PathVariable(PARAM_PASS_ID) Long passId) throws BaseException {
        Pass getPass = passService.findPassById(passId);
        return ResponseEntity.accepted().body(new SuccesResponse(getPass, null));

    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping(value = GYMS + GYM_ID + PASSES + PASS_ID, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> modificationPass(@RequestBody PassCreateRequest passCreateRequest, @PathVariable(PARAM_PASS_ID) Long passId) throws BaseException {
        if (Objects.isNull(passCreateRequest)) {
            return ResponseEntity.badRequest().body("null");
        }

        Pass updatedPass = Pass.builder().id(passId)
                .name(passCreateRequest.getName())
                .price(passCreateRequest.getPrice())
                .passType(passCreateRequest.getPassType())
                .duration(passCreateRequest.getDuration())
                .timeDuration(passCreateRequest.getTimeDuration())
                .passTimeDurationType(passCreateRequest.getPassTimeDurationType())
                .available(passCreateRequest.getAvailable()).build();
        Pass updated = passService.update(updatedPass);
        return ResponseEntity.ok().body(new SuccesResponse(updated, null));
    }


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = GYMS + GYM_ID + PASSES + PASS_ID)
    public ResponseEntity deletePass(@PathVariable(PARAM_PASS_ID) Long passId) throws BaseException {
        passService.deletePass(passId);
        return ResponseEntity.accepted().body("Delete Success!");
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = GYMS + GYM_ID + PASSES)
    public ResponseEntity<?> passListGetByGymId(@PathVariable(PARAM_GYM_ID) Long gymId)  throws BaseException {
        List<Pass> byGymIdAllPasses = passService.findByGymIdAllPasses(gymId);
        return ResponseEntity.accepted().body(new SuccesResponse(byGymIdAllPasses, null));
    }
}
