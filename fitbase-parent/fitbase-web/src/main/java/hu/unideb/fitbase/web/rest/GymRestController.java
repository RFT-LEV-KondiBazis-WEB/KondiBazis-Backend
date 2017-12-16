package hu.unideb.fitbase.web.rest;


import hu.unideb.fitbase.commons.pojo.exceptions.BaseException;
import hu.unideb.fitbase.commons.pojo.exceptions.ViolationException;
import hu.unideb.fitbase.commons.pojo.request.GymRequest;
import hu.unideb.fitbase.commons.pojo.response.GymListResponse;
import hu.unideb.fitbase.commons.pojo.response.GymSuccesCreateResponse;
import hu.unideb.fitbase.commons.pojo.response.GymSuccessUpdateResponse;
import hu.unideb.fitbase.service.api.domain.FitBaseUser;
import hu.unideb.fitbase.service.api.domain.Gym;
import hu.unideb.fitbase.service.api.domain.User;
import hu.unideb.fitbase.service.api.exception.ServiceException;
import hu.unideb.fitbase.service.api.service.GymService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static hu.unideb.fitbase.commons.path.container.PathContainer.GYM_ID;
import static hu.unideb.fitbase.commons.path.container.PathContainer.PARAM_GYM_ID;
import static hu.unideb.fitbase.commons.path.gym.GymPath.GYMS;

@RestController
public class GymRestController {

    @Autowired
    private GymService gymService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(path = GYMS)
    public ResponseEntity<?> postGym(@RequestBody GymRequest gymRequest)
            throws BaseException {
        if (Objects.isNull(gymRequest)) {
            return ResponseEntity.badRequest().body("null");
        }

        Gym gym = Gym.builder().name(gymRequest.getName()).city(gymRequest.getCity()).address(gymRequest.getAddress())
                .zipCode(gymRequest.getZipCode()).description(gymRequest.getDescription())
                .openingHours(gymRequest.getOpeningHours()).userList(Arrays.asList(getUser())).passes(Collections.emptyList()).build();

        ResponseEntity<?> result = null;
        try {
            gym = gymService.addGym(gym);
            result = ResponseEntity.accepted().body(new GymSuccesCreateResponse(gym));
        } catch (ServiceException e) {
            result = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return result;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(path = GYMS + GYM_ID)
    public ResponseEntity<?> putGym(@RequestBody GymRequest gymRequest, @PathVariable(PARAM_GYM_ID) Long gymId)
            throws BaseException {
        if (Objects.isNull(gymRequest)) {
            return ResponseEntity.badRequest().body("null");
        }

        Gym gym = Gym.builder().id(gymId).name(gymRequest.getName()).city(gymRequest.getCity())
                .address(gymRequest.getAddress()).zipCode(gymRequest.getZipCode())
                .description(gymRequest.getDescription()).openingHours(gymRequest.getOpeningHours())
                .userList(Arrays.asList(getUser())).build();

        gymService.updateGym(gym);
        return ResponseEntity.accepted().body(new GymSuccessUpdateResponse(gym));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(path = GYMS + GYM_ID)
    public ResponseEntity<?> deleteGym(@PathVariable(PARAM_GYM_ID) Long gymId) throws BaseException {
        gymService.deleteGym(gymId);
        return ResponseEntity.accepted().body(null);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(path = GYMS)
    public ResponseEntity<?> getAllGyms() throws ViolationException {
        List<Gym> gymByUser = gymService.findUsersGym(getUser());
        return ResponseEntity.accepted().body(new GymListResponse(gymByUser));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(path = GYMS + GYM_ID)
    public ResponseEntity showGym(@PathVariable(PARAM_GYM_ID) Long gymId) throws BaseException {
        Gym gym = gymService.findGymById(gymId);
        return ResponseEntity.accepted().body(new GymListResponse(gym));
    }

    private User getUser() {
        return ((FitBaseUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
    }

}