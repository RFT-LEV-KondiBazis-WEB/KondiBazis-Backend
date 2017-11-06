package hu.unideb.fitbase.service.api.converter;

import hu.unideb.fitbase.commons.pojo.enumeration.UserRole;
import hu.unideb.fitbase.commons.pojo.response.Data;
import hu.unideb.fitbase.persistence.entity.UserEntity;
import hu.unideb.fitbase.persistence.entity.UserRoleEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserEntityToUserConverter implements Converter<UserEntity, Data> {
    @Override
    public Data convert(UserEntity source) {
        return Data.builder()
                .id(source.getId())
                .username(source.getUsername())
                .email(source.getEmail())
                .password(source.getPassword())
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .rememberToken(source.getRememberToken())
                .createdDate(source.getCreatedDate())
                .enabled(source.isEnable())
                .userRole(convertRole(source.getUserRole()))
                .lastPasswordResetDate(source.getLastPasswordResetDate())
                .build();
    }

    private UserRole convertRole(UserRoleEntity role) {
        return UserRole.valueOf(role.toString());
    }
}
