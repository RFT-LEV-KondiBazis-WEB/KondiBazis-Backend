package hu.unideb.fitbase.web.rest;

import static hu.unideb.fitbase.commons.path.container.PathContainer.GYM_ID;
import static hu.unideb.fitbase.commons.path.container.PathContainer.PARAM_GYM_ID;
import static hu.unideb.fitbase.commons.path.gym.GymPath.GYMS;
import static hu.unideb.fitbase.commons.path.statistics.StatisticsPath.STAT_URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import hu.unideb.fitbase.commons.pojo.exceptions.ViolationException;
import hu.unideb.fitbase.commons.pojo.response.StatisticsResponse;
import hu.unideb.fitbase.service.api.domain.Statistics;
import hu.unideb.fitbase.service.api.domain.GymStatistics;
import hu.unideb.fitbase.service.api.service.CustomerHistoryService;
import hu.unideb.fitbase.service.api.service.CustomerService;
import hu.unideb.fitbase.service.api.service.GymService;
import hu.unideb.fitbase.service.api.service.PassService;

@RestController
public class StatisticsRestController {

    @Autowired
    GymService gymService;

    @Autowired
    PassService passService;

    @Autowired
    CustomerService customerService;
    
    @Autowired
    CustomerHistoryService customerHistoryService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping(path = STAT_URL)
    public ResponseEntity<?> getStatistics() throws ViolationException {
        Statistics statistics = Statistics.builder().countOfCustomers(customerService.countCustomers())
                .countOfGyms(gymService.countGyms()).countOfPasses(passService.countPasses()).build();
        return ResponseEntity.accepted().body(new StatisticsResponse(statistics));
    }
    
    @PreAuthorize("isAuthenticated()")
    @GetMapping(path = GYMS + GYM_ID + STAT_URL)
    public ResponseEntity<?> getGymStatistics(@PathVariable(PARAM_GYM_ID) Long gymId) throws ViolationException {
    	GymStatistics gymStatisctics = GymStatistics.builder().countPassesOnSysdateByGym(customerHistoryService.countPassesOnSysdateByGym(gymId))
    			.sumCostOfPassesOnSysdateByGym(customerHistoryService.sumCostOfPassesOnSysdateByGym(gymId))
    			.sumCustomersOnSysdateByGym(customerHistoryService.sumCustomersOnSysdateByGym(gymId)).build();
		return ResponseEntity.accepted().body(new StatisticsResponse(gymStatisctics));
    }
}
