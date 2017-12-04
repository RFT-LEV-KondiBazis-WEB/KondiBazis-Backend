package hu.unideb.fitbase.service.api.impl;

import hu.unideb.fitbase.persistence.entity.PassEntity;
import hu.unideb.fitbase.persistence.repository.PassRepository;
import hu.unideb.fitbase.service.api.converter.PassEntityListToPassListConverter;
import hu.unideb.fitbase.service.api.domain.Gym;
import hu.unideb.fitbase.service.api.domain.Pass;
import hu.unideb.fitbase.service.api.service.PassService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PassServiceImpl implements PassService {

    @Autowired
    private PassRepository passRepository;

    @Autowired
    private ConversionService conversionService;

    @Autowired
    private PassEntityListToPassListConverter passEntityListToPassListConverter;

    @Override
    public Pass addPass(Pass pass) {
        log.trace(">> save: [pass:{}]", pass);
        Pass convert = conversionService.convert(passRepository.save(conversionService.convert(pass, PassEntity.class)), Pass.class);
        log.trace("<< save: [pass:{}]", pass);
        return convert;
    }

//    @Override
//    public Pass findByPass(Long id) {
//        PassEntity byId = passRepository.findById(id);
//
//        return conversionService.convert(byId, Pass.class);
//    }

    @Override
    public void deletePass(Long id) {
        passRepository.delete(id);
    }

    @Override
    public List<Pass> findByGymIdAllPasses(Long gym) {
        List<PassEntity> byGym = passRepository.findByGymEntitiesId(gym);
        return passEntityListToPassListConverter.convert(byGym);
    }
}
