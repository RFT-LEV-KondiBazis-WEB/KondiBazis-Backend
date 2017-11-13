package hu.unideb.fitbase.service.api.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import hu.unideb.fitbase.commons.pojo.exceptions.ViolationException;
import hu.unideb.fitbase.persistence.entity.GymEntity;
import hu.unideb.fitbase.persistence.repository.GymRepository;
import hu.unideb.fitbase.service.api.domain.Gym;
import hu.unideb.fitbase.service.api.exception.ServiceException;
import hu.unideb.fitbase.service.api.service.GymService;
import hu.unideb.fitbase.service.api.validator.AbstractValidator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GymServiceImpl implements GymService{

	@Autowired
	private GymRepository gymRepository;
	
	@Autowired
	private ConversionService conversionService;
	
	@Autowired
	private AbstractValidator<Gym> gymAbstractValidator;

	@Override
	public Gym addGym(Gym gym) throws ViolationException, ServiceException {
		gymAbstractValidator.validate(gym);
        log.trace(">> save: [gym:{}]", gym);
        Gym convert = conversionService.convert(gymRepository.save(conversionService.convert(gym, GymEntity.class)), Gym.class);
        log.trace("<< save: [gym:{}]", gym);
        return convert;
	}

	@Override
	public Gym findByName(String name) {
		log.trace(">> findByName: [name:{}]", name);
		GymEntity gymEntity = gymRepository.findByName(name);
		Gym convert = conversionService.convert(gymEntity, Gym.class);
		log.trace("<< findByName: [name:{}]", name);
		return convert;
	}

}
