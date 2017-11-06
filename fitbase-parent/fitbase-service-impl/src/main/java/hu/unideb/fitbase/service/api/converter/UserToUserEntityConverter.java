package hu.unideb.fitbase.service.api.converter;

import hu.unideb.fitbase.commons.pojo.enumeration.UserRole;
import hu.unideb.fitbase.commons.pojo.response.Data;
import hu.unideb.fitbase.persistence.entity.UserEntity;
import hu.unideb.fitbase.persistence.entity.UserRoleEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class UserToUserEntityConverter implements Converter<Data, UserEntity> {

    @Override
    public UserEntity convert(Data source) {
        return UserEntity.builder()
                .id(source.getId())
                .username(source.getUsername())
                .email(source.getEmail())
                .password(source.getPassword())
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .rememberToken(source.getRememberToken())
                .createdDate(source.getCreatedDate())
                .enable(source.isEnabled())
                .userRoleEntity(convertRole(source.getUserRole()))
                .lastPasswordResetDate(source.getLastPasswordResetDate())
                .build();
    }

    private UserRoleEntity convertRole(UserRole role) {
        return UserRoleEntity.valueOf(role.toString());
    }

}
