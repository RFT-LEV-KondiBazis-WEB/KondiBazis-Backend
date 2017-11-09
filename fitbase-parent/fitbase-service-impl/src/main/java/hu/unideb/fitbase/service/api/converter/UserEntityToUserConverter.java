package hu.unideb.fitbase.service.api.converter;

import hu.unideb.fitbase.commons.pojo.enumeration.UserRole;
import hu.unideb.fitbase.persistence.entity.UserEntity;
import hu.unideb.fitbase.persistence.entity.UserRoleEntity;
import hu.unideb.fitbase.service.api.domain.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserEntityToUserConverter implements Converter<UserEntity, User> {
    @Override
    public User convert(UserEntity source) {
        return User.builder()
                .id(source.getId())
                .username(source.getUsername())
                .email(source.getEmail())
                .password(source.getPassword())
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .rememberToken(source.getRememberToken())
                .createdDate(source.getCreatedDate())
                .userRole(convertRole(source.getUserRole()))
                .build();
    }

    private UserRole convertRole(UserRoleEntity role) {
        return UserRole.valueOf(role.toString());
    }
}
