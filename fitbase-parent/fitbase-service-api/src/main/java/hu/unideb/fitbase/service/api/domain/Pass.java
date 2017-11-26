package hu.unideb.fitbase.service.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Pass {

    private Long id;

    private String name;

    private Boolean isLimited;

    private Integer limitNumber;

    private Integer duration;

    private Integer price;

    private Boolean available;

    @JsonIgnore
    private List<Gym> gymList;

}
