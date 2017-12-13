package hu.unideb.fitbase.service.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Gym domain class.
 */

@Data
@Builder
public class Gym implements Serializable {

    private static final long serialVersionUID = -230888379049145508L;

    private Long id;

    private String name;

    private String city;

    private String address;

    private String zipCode;

    private String description;

    private String openingHours;

    @JsonIgnore
    private List<User> userList;

    private List<Pass> passes;
}
