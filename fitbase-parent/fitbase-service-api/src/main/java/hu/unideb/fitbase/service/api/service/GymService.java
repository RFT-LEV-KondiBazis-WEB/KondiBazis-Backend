package hu.unideb.fitbase.service.api.service;

import hu.unideb.fitbase.commons.pojo.exceptions.ViolationException;
import hu.unideb.fitbase.service.api.domain.Gym;
import hu.unideb.fitbase.service.api.exception.ServiceException;

public interface GymService {
	
    Gym addGym(Gym gym) throws ViolationException, ServiceException;
    
    Gym findByName(String name);

    Gym findById(Long id);

    Gym updateGym(Gym gym);
    
}
