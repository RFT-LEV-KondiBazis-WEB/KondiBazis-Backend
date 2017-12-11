package hu.unideb.fitbase.service.api.impl;

import hu.unideb.fitbase.commons.pojo.exceptions.BaseException;
import hu.unideb.fitbase.commons.pojo.exceptions.ViolationException;
import hu.unideb.fitbase.persistence.entity.GymEntity;
import hu.unideb.fitbase.persistence.entity.PassEntity;
import hu.unideb.fitbase.persistence.repository.GymRepository;
import hu.unideb.fitbase.service.api.converter.GymEntityListToGymListConverter;
import hu.unideb.fitbase.service.api.domain.Gym;
import hu.unideb.fitbase.service.api.domain.User;
import hu.unideb.fitbase.service.api.exception.EntityNotFoundException;
import hu.unideb.fitbase.service.api.exception.ServiceException;
import hu.unideb.fitbase.service.api.service.GymService;
import hu.unideb.fitbase.service.api.validator.AbstractValidator;
import hu.unideb.fitbase.service.api.validator.GymValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class GymServiceImpl implements GymService {

    @Autowired
    private GymRepository gymRepository;

    @Autowired
    private ConversionService conversionService;

    @Autowired
    private GymValidator gymValidator;

    @Autowired
    private GymEntityListToGymListConverter gymEntityListToGymListConverter;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Gym addGym(Gym gym) throws ViolationException {
        gymValidator.validate(gym);
        log.trace(">> save: [gym:{}]", gym);
        Gym convert = conversionService.convert(gymRepository.save(conversionService.convert(gym, GymEntity.class)), Gym.class);
        log.trace("<< save: [gym:{}]", gym);
        return convert;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Gym updateGym(Gym gym) throws ViolationException {
    	gymValidator.validate(gym);
        log.trace(">> update: [gym:{}]", gym);
        Gym convert = conversionService.convert(gymRepository.save(conversionService.convert(gym, GymEntity.class)), Gym.class);
        log.trace("<< update: [gym:{}]", gym);
        return convert;
    }
    
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void deleteGym(Long id) throws BaseException{
        if (Objects.isNull(id)) {
            throw new ServiceException("id is NULL");
        }
        GymEntity gymEntity;
        try {
            gymEntity = gymRepository.findById(id);
        } catch (Exception e) {
            String errorMsg = String.format("Error on finding gym by id:%d.", id);
            throw new ServiceException(errorMsg, e);
        }
        if (Objects.isNull(gymEntity)) {
            String errorMsg = String.format("Gym with id:%d not found.", id);
            throw new EntityNotFoundException(errorMsg);
        } else {
            gymRepository.delete(id);
        }
	}
    
    @Override
    public Gym findByName(String name) throws BaseException {
        log.trace(">> findByName: [name:{}]", name);
        if (Objects.isNull(name)) {
            throw new ServiceException("name is NULL");
        }
        GymEntity gymEntity;
        try {
            gymEntity = gymRepository.findByName(name);
        } catch (Exception e) {
            String errorMsg = String.format("Error on finding gym by name:%s.", name);
            throw new ServiceException(errorMsg, e);
        }
        if (Objects.isNull(gymEntity)) {
            String errorMsg = String.format("Gym with name:%s not found.", name);
            throw new EntityNotFoundException(errorMsg);
        }
        Gym result = conversionService.convert(gymEntity, Gym.class);
        log.trace("<< findByName: [name:{}]", result);
        return result;
    }

    @Override
    public Gym findById(Long id) throws BaseException {
        log.trace(">> findById: [id:{}]", id);
        if (Objects.isNull(id)) {
            throw new ServiceException("id is NULL");
        }
        GymEntity gymEntity;
        try {
            gymEntity = gymRepository.findById(id);
        } catch (Exception e) {
            String errorMsg = String.format("Error on finding gym by id:%d.", id);
            throw new ServiceException(errorMsg, e);
        }
        if (Objects.isNull(gymEntity)) {
            String errorMsg = String.format("Gym with id:%d not found.", id);
            throw new EntityNotFoundException(errorMsg);
        }
        Gym result = conversionService.convert(gymEntity, Gym.class);
        log.trace("<< findById: [id:{}]", id);
        return result;
    }

    @Override
    public List<Gym> findUsersGym(User user) {
        List<GymEntity> byUsers = gymRepository.findByUsersId(user.getId());
        return gymEntityListToGymListConverter.convert(byUsers);
    }

}
