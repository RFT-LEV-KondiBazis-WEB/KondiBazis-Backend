package hu.unideb.fitbase.service.api.converter;

import hu.unideb.fitbase.persistence.entity.PassEntity;
import hu.unideb.fitbase.service.api.domain.Pass;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PassEntityToPassConverter implements Converter<PassEntity, Pass> {

    @Override
    public Pass convert(PassEntity source) {
        return Pass.builder()
                .name(source.getName())
                .isLimited(source.getIsLimited())
                .limitNumber(source.getLimitNumber())
                .duration(source.getDuration())
                .price(source.getPrice())
                .available(source.getAvailable())
                .build();
    }
}