package hu.unideb.fitbase.web.rest;

import hu.unideb.fitbase.commons.pojo.exceptions.ViolationException;
import hu.unideb.fitbase.commons.pojo.request.GymRequest;
import hu.unideb.fitbase.commons.pojo.response.GymSuccesCreateResponse;
import hu.unideb.fitbase.commons.pojo.response.GymSuccessUpdateResponse;
import hu.unideb.fitbase.commons.pojo.response.LoginSuccesResponse;
import hu.unideb.fitbase.commons.pojo.response.MetaResponse;
import hu.unideb.fitbase.service.api.domain.FitBaseUser;
import hu.unideb.fitbase.service.api.domain.Gym;
import hu.unideb.fitbase.service.api.domain.User;
import hu.unideb.fitbase.service.api.exception.ServiceException;
import hu.unideb.fitbase.service.api.service.GymService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static hu.unideb.fitbase.commons.path.gym.GymPath.GYMS_LIST_BY_URL;
import static hu.unideb.fitbase.commons.path.gym.GymPath.GYM_CREATE_URL;
import static hu.unideb.fitbase.commons.path.gym.GymPath.GYM_UPDATE_URL;
import static hu.unideb.fitbase.commons.path.gym.GymPath.GYM_DELETE_URL;
import static hu.unideb.fitbase.commons.path.gym.GymPath.PARAM_GYM_ID;
import static hu.unideb.fitbase.commons.path.gym.GymPath.GYM_ID;

@RestController
public class GymRestController {

	@Autowired
	private GymService gymService;

	@Value("${jwt.header}")
	private String tokenHeader;

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(path = GYM_CREATE_URL)
	public ResponseEntity<?> postGym(@RequestBody GymRequest gymRequest, HttpServletRequest request)
			throws ViolationException {
		if (Objects.isNull(gymRequest)) {
			return ResponseEntity.badRequest().body("null");
		}

		Gym gym = Gym.builder().name(gymRequest.getName()).city(gymRequest.getCity()).address(gymRequest.getAddress())
				.zipCode(gymRequest.getZipCode()).description(gymRequest.getDescription())
				.openingHours(gymRequest.getOpeningHours()).userList(Arrays.asList(getUser())).build();

		ResponseEntity<?> result = null;
		try {
			gymService.addGym(gym);
			result = ResponseEntity.accepted().body(new GymSuccesCreateResponse(gym));
		} catch (ServiceException e) {
			result = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("FAIL");
		}
		return result;
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping(path = GYM_UPDATE_URL + GYM_ID)
	public ResponseEntity<?> putGym(@RequestBody GymRequest gymRequest, @PathVariable(PARAM_GYM_ID) Long gymId)
			throws ViolationException {
		if (Objects.isNull(gymRequest)) {
			return ResponseEntity.badRequest().body("null");
		}

		Gym gym = Gym.builder().id(gymId).name(gymRequest.getName()).city(gymRequest.getCity()).address(gymRequest.getAddress())
				.zipCode(gymRequest.getZipCode()).description(gymRequest.getDescription())
				.openingHours(gymRequest.getOpeningHours()).userList(Arrays.asList(getUser())).build();

		ResponseEntity<?> result = null;
		gymService.updateGym(gym);
		result = ResponseEntity.accepted().body(new GymSuccessUpdateResponse(gym));
		return result;
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping(path = GYM_DELETE_URL + GYM_ID)
	public ResponseEntity<?> deleteGym(@RequestBody GymRequest gymRequest, @PathVariable(PARAM_GYM_ID) Long gymId)
			throws ViolationException {
		Gym gym = gymService.findById(gymId);
		gymService.deleteGym(gym);
		return ResponseEntity.accepted().body("Delete Success!");
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping(path = GYMS_LIST_BY_URL)
	public ResponseEntity<?> getUsersGyms() throws ViolationException {
		List<Gym> gymByUser = gymService.findUsersGym(getUser());
		return ResponseEntity.accepted().body(new LoginSuccesResponse(gymByUser, new MetaResponse(null)));
	}

	private User getUser() {
		return ((FitBaseUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
	}

}
