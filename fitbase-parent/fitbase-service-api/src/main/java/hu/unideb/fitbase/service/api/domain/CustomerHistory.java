package hu.unideb.fitbase.service.api.domain;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 * Customer history domain class.
 */
@Data
@Builder
public class CustomerHistory implements Serializable {

    private Date passStartDate;

    private LocalDate passEndDate;

    private LocalDate passBuyDate;

    private boolean status;

    private String passName;

    private String passType;

    private Integer passPrice;

    private Customer customer;

    private Gym gym;

}
