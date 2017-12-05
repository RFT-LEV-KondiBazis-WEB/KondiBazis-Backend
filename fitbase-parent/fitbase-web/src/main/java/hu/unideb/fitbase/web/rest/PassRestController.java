package hu.unideb.fitbase.web.rest;

import hu.unideb.fitbase.commons.pojo.enumeration.PassType;
import hu.unideb.fitbase.commons.pojo.exceptions.ViolationException;
import hu.unideb.fitbase.commons.pojo.request.SuitablePassCreateRequest;
import hu.unideb.fitbase.commons.pojo.request.TimeLimitedPassCreateRequest;
import hu.unideb.fitbase.commons.pojo.response.PassSuccesResponse;
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

import static hu.unideb.fitbase.commons.path.pass.PassPath.*;

@RestController
public class PassRestController {

    @Autowired
    private PassService passService;

    @Autowired
    private GymService gymService;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = PASS_CREATE_SUITBALE + GYM_ID, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createSuitablePass(@RequestBody SuitablePassCreateRequest suitablePassCreateRequest, @PathVariable(PARAM_GYM_ID) Long gymId) throws ViolationException {

        Gym gym = gymService.findById(gymId);

        Pass createdPass = createSuitablePass(suitablePassCreateRequest, gym);
        ResponseEntity result;
        try {
            Pass c = passService.addPass(createdPass);
            result = ResponseEntity.accepted().body(new PassSuccesResponse(c));
        } catch (ServiceException e) {
            result = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("FAIL");
        }

        return result;
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = PASS_CREATE_TIME_LIMITED + GYM_ID, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createTimeLimitedPass(@RequestBody TimeLimitedPassCreateRequest timeLimitedPassCreateRequest, @PathVariable(PARAM_GYM_ID) Long gymId) throws ViolationException {

        Gym gym = gymService.findById(gymId);

        Pass createdPass = createTimeLimitedPass(timeLimitedPassCreateRequest, gym);
        ResponseEntity result;
        try {
            passService.addPass(createdPass);
            result = ResponseEntity.accepted().body(new PassSuccesResponse(createdPass));
        } catch (ServiceException e) {
            result = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("FAIL");
        }

        return result;
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = PASS_GET_INFO + PASS_ID)
    public ResponseEntity getPass(@PathVariable(PARAM_PASS_ID) Long passId) {

        Pass getPass = passService.findPassById(passId);

        return ResponseEntity.accepted().body(new PassSuccesResponse(getPass));

    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = PASS_UPDATE_SUITBALE + PASS_ID, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> modificationPass(@RequestBody SuitablePassCreateRequest suitablePassCreateRequest, @PathVariable(PARAM_PASS_ID) Long passId) throws ViolationException {
        Pass passById = passService.findPassById(passId);

        passById.setName(suitablePassCreateRequest.getName());


//        Pass o = Pass.builder()
//                .id(passId)
//                .name(suitablePassCreateRequest.getName())
//                .price(suitablePassCreateRequest.getPrice())
//                .passType(PassType.SUITABLE)
//                .duration(suitablePassCreateRequest.getDuration())
//                .timeDuration(suitablePassCreateRequest.getTimeDuration())
//                .passTimeDurationType(suitablePassCreateRequest.getPassTimeDurationType())
//                .available(suitablePassCreateRequest.getAvailable())
//                .build();

      //TODO
        passService.update(passById);
        return ResponseEntity.ok().body("Módosítva");
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

    private Pass createSuitablePass(SuitablePassCreateRequest suitablePassCreateRequest, Gym gym) {
        return Pass.builder()
                .name(suitablePassCreateRequest.getName())
                .price(suitablePassCreateRequest.getPrice())
                .passType(PassType.SUITABLE)
                .duration(suitablePassCreateRequest.getDuration())
                .timeDuration(suitablePassCreateRequest.getTimeDuration())
                .passTimeDurationType(suitablePassCreateRequest.getPassTimeDurationType())
                .available(suitablePassCreateRequest.getAvailable())
                .gymList(Arrays.asList(gym))
                .build();
    }

    private Pass createTimeLimitedPass(TimeLimitedPassCreateRequest timeLimitedPassCreateRequest, Gym gym) {
        return Pass.builder()
                .name(timeLimitedPassCreateRequest.getName())
                .price(timeLimitedPassCreateRequest.getPrice())
                .passType(PassType.TIME_LIMITED)
                .timeDuration(timeLimitedPassCreateRequest.getTimeDuration())
                .passTimeDurationType(timeLimitedPassCreateRequest.getPassTimeDurationType())
                .available(timeLimitedPassCreateRequest.getAvailable())
                .gymList(Arrays.asList(gym))
                .build();
    }


}
