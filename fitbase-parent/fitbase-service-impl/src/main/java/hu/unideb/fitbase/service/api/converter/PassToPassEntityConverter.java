package hu.unideb.fitbase.service.api.converter;

import hu.unideb.fitbase.persistence.entity.PassEntity;
import hu.unideb.fitbase.service.api.domain.Pass;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PassToPassEntityConverter implements Converter<Pass, PassEntity> {
    
    @Override
    public PassEntity convert(Pass source) {
        return PassEntity.builder()
                .name(source.getName())
                .isLimited(source.getIsLimited())
                .limitNumber(source.getLimitNumber())
                .duration(source.getDuration())
                .price(source.getPrice())
                .available(source.getAvailable())
                .build();
    }
}
