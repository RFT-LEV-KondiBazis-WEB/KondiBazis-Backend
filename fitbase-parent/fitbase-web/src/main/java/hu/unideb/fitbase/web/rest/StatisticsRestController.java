package hu.unideb.fitbase.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.unideb.fitbase.commons.pojo.exceptions.ViolationException;
import hu.unideb.fitbase.commons.pojo.response.StatisticsResponse;
import hu.unideb.fitbase.service.api.domain.Statistics;
import hu.unideb.fitbase.service.api.service.CustomerService;
import hu.unideb.fitbase.service.api.service.GymService;
import hu.unideb.fitbase.service.api.service.PassService;

import static hu.unideb.fitbase.commons.path.statistics.StatisticsPath.STAT_URL;

@RestController
public class StatisticsRestController {

	@Autowired
	GymService gymService;

	@Autowired
	PassService passService;

	@Autowired
	CustomerService customerService;

	@PreAuthorize("isAuthenticated()")
	@GetMapping(path = STAT_URL)
	public ResponseEntity<?> getStatistics() throws ViolationException {
		Statistics statistics = Statistics.builder().countOfCustomers(customerService.countCustomers())
				.countOfGyms(gymService.countGyms()).countOfPasses(passService.countPasses()).build();
		return ResponseEntity.accepted().body(new StatisticsResponse(statistics));
	}
}
