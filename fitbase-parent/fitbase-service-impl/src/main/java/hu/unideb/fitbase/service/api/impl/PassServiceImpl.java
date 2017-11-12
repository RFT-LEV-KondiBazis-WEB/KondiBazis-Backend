package hu.unideb.fitbase.service.api.impl;

import hu.unideb.fitbase.persistence.entity.PassEntity;
import hu.unideb.fitbase.persistence.repository.PassRepository;
import hu.unideb.fitbase.service.api.domain.Pass;
import hu.unideb.fitbase.service.api.service.PassService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PassServiceImpl implements PassService {

    @Autowired
    private PassRepository passRepository;

    @Autowired
    private ConversionService conversionService;

    @Override
    public Pass addPass(Pass pass) {
        log.trace(">> save: [pass:{}]", pass);
        Pass convert = conversionService.convert(passRepository.save(conversionService.convert(pass, PassEntity.class)), Pass.class);
        log.trace("<< save: [pass:{}]", pass);
        return convert;
    }
}
