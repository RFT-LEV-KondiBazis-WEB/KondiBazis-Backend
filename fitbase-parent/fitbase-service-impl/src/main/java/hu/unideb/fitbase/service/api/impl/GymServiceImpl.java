package hu.unideb.fitbase.service.api.impl;

import hu.unideb.fitbase.commons.pojo.exceptions.ViolationException;
import hu.unideb.fitbase.persistence.entity.GymEntity;
import hu.unideb.fitbase.persistence.repository.GymRepository;
import hu.unideb.fitbase.service.api.converter.GymEntityListToGymListConverter;
import hu.unideb.fitbase.service.api.domain.Gym;
import hu.unideb.fitbase.service.api.domain.User;
import hu.unideb.fitbase.service.api.exception.ServiceException;
import hu.unideb.fitbase.service.api.service.GymService;
import hu.unideb.fitbase.service.api.validator.AbstractValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class GymServiceImpl implements GymService {

    @Autowired
    private GymRepository gymRepository;

    @Autowired
    private ConversionService conversionService;

    @Autowired
    private AbstractValidator<Gym> gymAbstractValidator;

    @Autowired
    private GymEntityListToGymListConverter gymEntityListToGymListConverter;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Gym addGym(Gym gym) throws ViolationException, ServiceException {
        gymAbstractValidator.validate(gym);
        log.trace(">> save: [gym:{}]", gym);
        Gym convert = conversionService.convert(gymRepository.save(conversionService.convert(gym, GymEntity.class)), Gym.class);
        log.trace("<< save: [gym:{}]", gym);
        return convert;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Gym updateGym(Gym gym) throws ViolationException {
    	gymAbstractValidator.validate(gym);
        log.trace(">> update: [gym:{}]", gym);
        Gym convert = conversionService.convert(gymRepository.save(conversionService.convert(gym, GymEntity.class)), Gym.class);
        log.trace("<< update: [gym:{}]", gym);
        return convert;
    }
    
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void deleteGym(Gym gym) throws ViolationException {
		log.trace(">> delete: [gym:{}]", gym);
		gymRepository.delete(conversionService.convert(gym, GymEntity.class));
		log.trace("<< delete: [gym:{}]", gym);
	}
    
    @Override
    public Gym findByName(String name) {
        log.trace(">> findByName: [name:{}]", name);
        GymEntity gymEntity = gymRepository.findByName(name);
        Gym convert = conversionService.convert(gymEntity, Gym.class);
        log.trace("<< findByName: [name:{}]", convert);
        return convert;
    }

    @Override
    public Gym findById(Long id) {
        log.trace(">> findById: [id:{}]", id);
        GymEntity gymEntity = gymRepository.findById(id);
        Gym convert = conversionService.convert(gymEntity, Gym.class);
        log.trace(">> findById: [gym:{}]", convert);
        return convert;
    }

    public List<Gym> findUsersGym(User user) {
        List<GymEntity> byUsers = gymRepository.findByUsersId(user.getId());
        return gymEntityListToGymListConverter.convert(byUsers);
    }

}
