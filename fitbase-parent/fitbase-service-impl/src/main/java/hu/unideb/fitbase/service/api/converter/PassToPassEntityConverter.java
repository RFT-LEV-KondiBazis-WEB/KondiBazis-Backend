package hu.unideb.fitbase.service.api.converter;

import hu.unideb.fitbase.persistence.entity.PassEntity;
import hu.unideb.fitbase.service.api.domain.Pass;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PassToPassEntityConverter implements Converter<Pass, PassEntity> {


    @Override
    public PassEntity convert(Pass source) {
        int duration;
        int timeDuration;
        if (source.getDuration().isEmpty()) {
            duration = 0;
        } else {
            duration = Integer.parseInt(source.getDuration());
        }

        if(source.getTimeDuration().isEmpty()){
            timeDuration = 0;
        } else {
            timeDuration = Integer.parseInt(source.getTimeDuration());
        }
        return PassEntity.builder()
                .id(source.getId())
                .name(source.getName())
                .price(source.getPrice())
                .passType(source.getPassType())
                .duration(duration)
                .timeDuration(timeDuration)
                .passTimeDurationType(source.getPassTimeDurationType())
                .available(source.getAvailable())
                .build();
    }
}
