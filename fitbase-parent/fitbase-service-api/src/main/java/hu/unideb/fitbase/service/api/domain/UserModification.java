package hu.unideb.fitbase.service.api.domain;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class UserModification implements Serializable {

	private static final long serialVersionUID = 4013160208198898166L;

	private Long id;

    private String email;

    private String password;

    private String passwordConfirm;

    private String firstName;

    private String lastName;
}
