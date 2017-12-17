package hu.unideb.fitbase.service.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private String passType;

    private String duration;

    private String timeDuration;

    private String passTimeDurationType;

    private Boolean available;

}
