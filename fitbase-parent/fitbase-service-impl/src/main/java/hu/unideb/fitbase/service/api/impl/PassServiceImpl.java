package hu.unideb.fitbase.service.api.impl;

import hu.unideb.fitbase.commons.pojo.exceptions.BaseException;
import hu.unideb.fitbase.commons.pojo.exceptions.ViolationException;
import hu.unideb.fitbase.persistence.entity.PassEntity;
import hu.unideb.fitbase.persistence.repository.PassRepository;
import hu.unideb.fitbase.service.api.converter.PassEntityListToPassListConverter;
import hu.unideb.fitbase.service.api.domain.Pass;
import hu.unideb.fitbase.service.api.exception.EntityNotFoundException;
import hu.unideb.fitbase.service.api.exception.ServiceException;
import hu.unideb.fitbase.service.api.service.PassService;
import hu.unideb.fitbase.service.api.validator.AbstractValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class PassServiceImpl implements PassService {

    @Autowired
    private PassRepository passRepository;

    @Autowired
    private ConversionService conversionService;

    @Autowired
    private PassEntityListToPassListConverter passEntityListToPassListConverter;

    @Autowired
    private AbstractValidator<Pass> passCreateRequestAbstractValidator;


    @Override
    public Pass addPass(Pass pass) throws ViolationException {
        log.trace(">> save: [pass:{}]", pass);
        passCreateRequestAbstractValidator.validate(pass);
        Pass convert = conversionService.convert(passRepository.save(conversionService.convert(pass, PassEntity.class)), Pass.class);
        log.trace("<< save: [pass:{}]", pass);
        return convert;
    }

    @Override
    public Pass findPassById(Long id) throws BaseException {
        log.trace(">> findById: [id:{}]", id);
        if (Objects.isNull(id)) {
            throw new ServiceException("id is NULL");
        }
        PassEntity passEntity;
        try {
            passEntity = passRepository.findById(id);
        } catch (Exception e) {
            String errorMsg = String.format("Error on finding pass by id:%d.", id);
            throw new ServiceException(errorMsg, e);
        }
        if (Objects.isNull(passEntity)) {
            String errorMsg = String.format("Pass with id:%d not found.", id);
            throw new EntityNotFoundException(errorMsg);
        }
        Pass result = conversionService.convert(passEntity, Pass.class);
        log.trace("<< findById: [id:{}]", id);
        return result;
    }

    @Override
    public void deletePass(Long id) {
        passRepository.delete(id);
    }

    @Override
    public List<Pass> findByGymIdAllPasses(Long gym) {
        List<PassEntity> byGym = passRepository.findByGymEntitiesId(gym);
        return passEntityListToPassListConverter.convert(byGym);
    }

    @Override
    public Pass update(Pass pass) throws ViolationException {
        passCreateRequestAbstractValidator.validate(pass);
        log.trace(">> save: [pass:{}]", pass);
        Pass convert = conversionService.convert(passRepository.save(conversionService.convert(pass, PassEntity.class)), Pass.class);
        log.trace("<< save: [pass:{}]", pass);
        return convert;
    }

    @Override
    public Pass findPassByName(String name) {
        log.trace(">> findByName: [name:{}]", name);
        PassEntity passEntity = passRepository.findByName(name);
        Pass convert = conversionService.convert(passEntity, Pass.class);
        log.trace("<< findByName: [name:{}]", convert);
        return convert;
    }
}
