package hu.unideb.fitbase.service.api.converter;

import hu.unideb.fitbase.persistence.entity.GymEntity;
import hu.unideb.fitbase.persistence.entity.PassEntity;
import hu.unideb.fitbase.service.api.domain.Gym;
import hu.unideb.fitbase.service.api.domain.Pass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PassToPassEntityConverter implements Converter<Pass, PassEntity> {

    @Autowired
    private GymToGymEntityConverter gymToGymEntityConverter;

    @Override
    public PassEntity convert(Pass source) {
        return PassEntity.builder()
                .id(source.getId())
                .name(source.getName())
                .price(source.getPrice())
                .passType(source.getPassType())
                .duration(source.getDuration())
                .timeDuration(source.getTimeDuration())
                .passTimeDurationType(source.getPassTimeDurationType())
                .available(source.getAvailable())
                .gymEntities(convert(source.getGymList()))
                .build();
    }

    private List<GymEntity> convert(List<Gym> source) {
        return source.stream().map(gymEntity -> gymToGymEntityConverter.convert(gymEntity)).collect(Collectors.toList());
    }

}
