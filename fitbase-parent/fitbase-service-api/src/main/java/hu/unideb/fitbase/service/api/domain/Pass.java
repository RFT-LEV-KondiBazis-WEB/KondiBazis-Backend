package hu.unideb.fitbase.service.api.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Pass {

    private String name;

    private Boolean isLimited;

    private Integer limitNumber;

    private Integer duration;

    private Integer price;

    private Boolean available;

    private List<User> userList;
}
