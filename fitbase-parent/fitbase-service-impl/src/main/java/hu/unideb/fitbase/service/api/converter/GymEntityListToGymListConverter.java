package hu.unideb.fitbase.service.api.converter;

import hu.unideb.fitbase.persistence.entity.GymEntity;
import hu.unideb.fitbase.service.api.domain.Gym;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GymEntityListToGymListConverter implements Converter<List<GymEntity>, List<Gym>> {

    @Autowired
    private GymEntityToGymConverter gymEntityToGymConverter;

    @Override
    public List<Gym> convert(List<GymEntity> source) {
        return source.stream().map(gym -> gymEntityToGymConverter.convert(gym)).collect(Collectors.toList());
    }

}
