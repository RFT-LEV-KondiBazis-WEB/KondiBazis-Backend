package hu.unideb.fitbase.service.api.service;

import hu.unideb.fitbase.commons.pojo.exceptions.BaseException;
import hu.unideb.fitbase.commons.pojo.exceptions.ViolationException;
import hu.unideb.fitbase.service.api.domain.Gym;
import hu.unideb.fitbase.service.api.domain.User;
import hu.unideb.fitbase.service.api.exception.ServiceException;

import java.util.List;

public interface GymService {

    Gym addGym(Gym gym) throws BaseException;

    Gym updateGym(Gym gym) throws BaseException;

    void deleteGym(Long id) throws BaseException;

    Gym findByName(String name) throws BaseException;

    Gym findGymById(Long id) throws BaseException;

    List<Gym> findUsersGym(User user);
  
    List<Gym> findAll();
    
    Long countGyms();

}
