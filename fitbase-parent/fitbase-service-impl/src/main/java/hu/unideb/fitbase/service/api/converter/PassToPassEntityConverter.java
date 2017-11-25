package hu.unideb.fitbase.service.api.converter;

import hu.unideb.fitbase.persistence.entity.GymEntity;
import hu.unideb.fitbase.persistence.entity.PassEntity;
import hu.unideb.fitbase.persistence.entity.UserEntity;
import hu.unideb.fitbase.service.api.domain.Gym;
import hu.unideb.fitbase.service.api.domain.Pass;
import hu.unideb.fitbase.service.api.domain.User;
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
                .isLimited(source.getIsLimited())
                .limitNumber(source.getLimitNumber())
                .duration(source.getDuration())
                .price(source.getPrice())
                .available(source.getAvailable())
                .gymEntity(convert(source.getGymList()))
                .build();
    }

    private List<GymEntity> convert(List<Gym> source){
        return source.stream().map(gymEntity -> gymToGymEntityConverter.convert(gymEntity)).collect(Collectors.toList());
    }
}
