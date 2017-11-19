package hu.unideb.fitbase.service.api.converter;

import hu.unideb.fitbase.persistence.entity.PassEntity;
import hu.unideb.fitbase.persistence.entity.UserEntity;
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
    private UserToUserEntityConverter userToUserEntityConverter;

    @Override
    public PassEntity convert(Pass source) {
        return PassEntity.builder()
                .name(source.getName())
                .isLimited(source.getIsLimited())
                .limitNumber(source.getLimitNumber())
                .duration(source.getDuration())
                .price(source.getPrice())
                .available(source.getAvailable())
                .userEntities(convert(source.getUserList()))
                .build();
    }

    private List<UserEntity> convert(List<User> source){
        return source.stream().map(userEntity -> userToUserEntityConverter.convert(userEntity)).collect(Collectors.toList());
    }
}
