package hu.unideb.fitbase.service.api.converter;

import hu.unideb.fitbase.commons.pojo.enumeration.PassType;
import hu.unideb.fitbase.persistence.entity.GymEntity;
import hu.unideb.fitbase.persistence.entity.PassEntity;
import hu.unideb.fitbase.persistence.entity.PassTypeEntity;
import hu.unideb.fitbase.service.api.domain.Gym;
import hu.unideb.fitbase.service.api.domain.Pass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PassEntityToPassConverter implements Converter<PassEntity, Pass> {

    @Autowired
    private GymEntityToGymConverter gymEntityToGymConverter;

    @Override
    public Pass convert(PassEntity source) {
        return Pass.builder()
                .id(source.getId())
                .name(source.getName())
                .price(source.getPrice())
                .passType(convertType(source.getPassTypeEntity()))
                .duration(source.getDuration())
                .timeDuration(source.getTimeDuration())
                .available(source.getAvailable())
                .gymList(convert(source.getGymEntities()))
                .build();
    }

    private List<Gym> convert(List<GymEntity> source) {
        return source.stream().map(gym -> gymEntityToGymConverter.convert(gym)).collect(Collectors.toList());
    }

    private PassType convertType(PassTypeEntity type) {
        return PassType.valueOf(type.toString());
    }
}