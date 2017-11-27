package hu.unideb.fitbase.service.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hu.unideb.fitbase.commons.pojo.enumeration.PassType;
import hu.unideb.fitbase.persistence.entity.PassTypeEntity;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class Pass implements Serializable {

    private Long id;

    private String name;

    private Integer price;

    private PassType passType;

    private Integer duration;

    private Integer timeDuration;

    private Boolean available;

    private List<Gym> gymList;

}
