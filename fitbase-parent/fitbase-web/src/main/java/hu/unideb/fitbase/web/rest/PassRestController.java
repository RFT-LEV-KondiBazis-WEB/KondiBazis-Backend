package hu.unideb.fitbase.web.rest;

import hu.unideb.fitbase.commons.pojo.enumeration.PassType;
import hu.unideb.fitbase.commons.pojo.exceptions.ViolationException;
import hu.unideb.fitbase.commons.pojo.request.PassCreateRequest;
import hu.unideb.fitbase.commons.pojo.request.SuitablePassCreateRequest;
import hu.unideb.fitbase.commons.pojo.request.TimeLimitedPassCreateRequest;
import hu.unideb.fitbase.commons.pojo.response.PassCreateSuccesResponse;
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

import static hu.unideb.fitbase.commons.path.pass.PassPath.*;

@RestController
public class PassRestController {

    @Autowired
    private PassService passService;

    @Autowired
    private GymService gymService;

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value =  PASS_CREATE + PASS_TYPE + GYM_ID, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createPass(@RequestBody PassCreateRequest passCreateRequest, @PathVariable(PARAM_PASS_TYPE) String passType, @PathVariable(PARAM_GYM_ID) Long gymId) throws ViolationException {


        //                case "TIME_LIMITED":
//                    pass = Pass.builder()
//                        .name(timeLimitedPassCreateRequest.getName())
//                        .price(timeLimitedPassCreateRequest.getPrice())
//                        .passType(PassType.TIME_LIMITED)
//                        .timeDuration(timeLimitedPassCreateRequest.getTimeDuration())
//                        .available(timeLimitedPassCreateRequest.getAvailable())
//                        .gymList(Arrays.asList(gyms))
//                        .build();
//
//                passService.addPass(pass);
//                return ResponseEntity.accepted().body(new PassCreateSuccesResponse(pass));
//            }

        ResponseEntity result;
        Gym gyms = gymService.findById(gymId);
        Pass pass;
        TimeLimitedPassCreateRequest timeLimitedPassCreateRequest = null;

      //  try {
//            switch (passType){
//                case "suitable":
//                    pass = Pass.builder()
//                        .name(suitablePassCreateRequest.getName())
//                        .price(suitablePassCreateRequest.getPrice())
//                        .passType(PassType.SUITABLE)
//                        .duration(suitablePassCreateRequest.getDuration())
//                        .timeDuration(null)
//                        .available(suitablePassCreateRequest.getAvailable())
//                        .gymList(Arrays.asList(gyms))
//                        .build();

             //   passService.addPass(pass);
           //    result =  ResponseEntity.accepted().body(new PassCreateSuccesResponse(pass));

       // } catch (ServiceException e) {
          //  result = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
       // }

        return null;
    }

//    @PreAuthorize("isAuthenticated()")
//    @RequestMapping(value = PASS_MODIFICATION_URL + PASS_ID, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> modificationPass(@RequestBody PassCreateRequest passCreateRequest, @PathVariable(PARAM_PASS_ID) Long passId) throws ViolationException {
//        Pass passById = passService.findPassById(passId);
//      //TODO
//        passService.update(passById);
//        return ResponseEntity.ok().body("Módosítva");
//    }

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

//    private Pass createPass(PassCreateRequest passCreateRequest, Gym gym) {
//        return Pass.builder()
//                .name(passCreateRequest.getName())
//                .isLimited(passCreateRequest.getIsLimited())
//                .limitNumber(passCreateRequest.getLimitNumber())
//                .duration(passCreateRequest.getDuration())
//                .price(passCreateRequest.getPrice())
//                .available(passCreateRequest.getAvailable())
//                .gymList(Arrays.asList(gym))
//                .build();
//    }


}
