package hu.unideb.fitbase.service.api.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Pass {

    private String name;

    private Boolean isLimited;

    private Integer limitNumber;

    private Integer duration;

    private Integer price;

    private Boolean available;
}
