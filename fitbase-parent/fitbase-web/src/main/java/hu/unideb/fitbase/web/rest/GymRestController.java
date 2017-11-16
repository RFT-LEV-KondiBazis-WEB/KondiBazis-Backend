package hu.unideb.fitbase.web.rest;

import hu.unideb.fitbase.commons.pojo.exceptions.ViolationException;
import hu.unideb.fitbase.commons.pojo.request.GymRequest;
import hu.unideb.fitbase.service.api.domain.FitBaseUser;
import hu.unideb.fitbase.service.api.domain.Gym;
import hu.unideb.fitbase.service.api.domain.User;
import hu.unideb.fitbase.service.api.exception.ServiceException;
import hu.unideb.fitbase.service.api.service.GymService;
import hu.unideb.fitbase.web.token.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Objects;

import static hu.unideb.fitbase.commons.path.gym.GymPath.GYM_CREATE_URL;

@CrossOrigin(maxAge = 3600)
@RestController
public class GymRestController {

    @Autowired
    GymService gymService;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @CrossOrigin(origins = "http://localhost:8081")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(path = GYM_CREATE_URL)
    public ResponseEntity<?> putGym(@RequestBody GymRequest gymRequest, HttpServletRequest request) throws ViolationException {
        if (Objects.isNull(gymRequest)) {
            return ResponseEntity.badRequest().body("null");
        }
        Gym gym = Gym.builder()
                .name(gymRequest.getName())
                .city(gymRequest.getCity())
                .address(gymRequest.getAddress())
                .zipCode(gymRequest.getZipCode())
                .description(gymRequest.getDescription())
                .openingHours(gymRequest.getOpeningHours())
                .userList(Arrays.asList(getUser()))
                .build();

        ResponseEntity<?> result = null;
        try {
            gymService.save(gym);
            result = ResponseEntity.ok().body("OK");
        } catch (ServiceException e) {
            result = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("FAIL");
        } catch (ViolationException e) {
            e.printStackTrace();
        }
        return result;
    }

    private User getUser() {
        return ((FitBaseUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
    }

}