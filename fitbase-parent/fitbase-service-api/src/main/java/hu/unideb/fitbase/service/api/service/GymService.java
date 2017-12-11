package hu.unideb.fitbase.service.api.service;

import hu.unideb.fitbase.commons.pojo.exceptions.ViolationException;
import hu.unideb.fitbase.service.api.domain.Gym;
import hu.unideb.fitbase.service.api.domain.User;
import hu.unideb.fitbase.service.api.exception.ServiceException;

import java.util.List;

public interface GymService {
	
    Gym addGym(Gym gym) throws ViolationException, ServiceException;

    Gym updateGym(Gym gym) throws ViolationException;
    
    void deleteGym(Long id) throws ViolationException;;
    
    Gym findByName(String name);

    Gym findById(Long id);
    
    List<Gym> findUsersGym(User user);
    
    List<Gym> findAll();
    
    Long countGyms();

}
