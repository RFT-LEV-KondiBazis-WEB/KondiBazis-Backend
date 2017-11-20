package hu.unideb.fitbase.service.api.converter;

import hu.unideb.fitbase.commons.pojo.enumeration.UserRole;
import hu.unideb.fitbase.commons.pojo.request.ManagerRegistrationRequest;
import hu.unideb.fitbase.commons.pojo.request.RegistrationRequest;
import hu.unideb.fitbase.service.api.domain.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import sun.misc.Contended;

@Component
public class ManagerRequestToUserConverter implements Converter<ManagerRegistrationRequest, User> {

    private static final BCryptPasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    @Override
    public User convert(ManagerRegistrationRequest source) {
        if (source == null) {
            return null;
        }
        return User.builder()
                .username(source.getUsername())
                .email(source.getEmail())
                .password(PASSWORD_ENCODER.encode(source.getPassword()))
                .passwordConfirm(PASSWORD_ENCODER.encode(source.getPassword()))
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .rememberToken(source.getRememberToken())
                .createdDate(source.getCreatedDate())
                .userRole(UserRole.MANAGER)
                .build();
    }

}
