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
                .id(source.getId())
                .name(source.getName())
                .price(source.getPrice())
                .passType(source.getPassType())
                .duration(String.valueOf(source.getDuration()))
                .timeDuration(String.valueOf(source.getTimeDuration()))
                .passTimeDurationType(source.getPassTimeDurationTypeEntity())
                .available(source.getAvailable())
                .build();
    }
}