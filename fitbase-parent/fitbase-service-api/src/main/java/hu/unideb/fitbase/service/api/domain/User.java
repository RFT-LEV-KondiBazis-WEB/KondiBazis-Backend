package hu.unideb.fitbase.service.api.domain;

import hu.unideb.fitbase.commons.pojo.enumeration.UserRole;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

/**
 * User domain class.
 */
@Builder
@Data
public class User {
    private Long id;

    private String username;

    private String email;

    private String password;

    private String passwordConfirm;

    private String firstName;

    private String lastName;

    private String rememberToken;

    private LocalDate createdDate;

    private UserRole userRole;
}
