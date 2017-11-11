package hu.unideb.fitbase.service.api.service;

import hu.unideb.fitbase.commons.pojo.exceptions.ViolationException;
import hu.unideb.fitbase.service.api.domain.Gym;
import hu.unideb.fitbase.service.api.exception.ServiceException;

public interface GymService {
/*
	Gym addGym(GymRequest gymRequest) throws ViolationException, ServiceException;
*/	
	Gym save(Gym gym) throws ViolationException, ServiceException;
}
