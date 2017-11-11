package hu.unideb.fitbase.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hu.unideb.fitbase.commons.pojo.exceptions.ViolationException;
import hu.unideb.fitbase.commons.pojo.request.GymRequest;
import hu.unideb.fitbase.service.api.domain.Gym;
import hu.unideb.fitbase.service.api.exception.ServiceException;
import hu.unideb.fitbase.service.api.service.GymService;

import static hu.unideb.fitbase.commons.path.gym.GymPath.GYM_URL;

import java.util.Objects;

@RestController
public class GymRestController {

	@Autowired
	GymService gymService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(path = GYM_URL)
	public ResponseEntity<?> putGym(@RequestBody GymRequest gymRequest) throws ViolationException {
		if(Objects.isNull(gymRequest)){
            return ResponseEntity.badRequest().body("null");
        }
		Gym gym = Gym.builder()
				.name(gymRequest.getName())
				.city(gymRequest.getCity())
				.address(gymRequest.getAddress())
				.zipCode(gymRequest.getZipCode())
				.description(gymRequest.getDescription())
				.openingHours(gymRequest.getOpeningHours())
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
	
}
