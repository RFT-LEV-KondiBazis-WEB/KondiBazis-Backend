package hu.unideb.fitbase.persistence.entity;

import hu.unideb.fitbase.commons.pojo.enumeration.PassTimeDurationType;
import lombok.*;

import javax.persistence.*;
import java.util.List;

import static hu.unideb.fitbase.commons.pojo.table.ColumnName.CustomerColumName.COLUMN_NAME_CUSTOMER_ID;
import static hu.unideb.fitbase.commons.pojo.table.ColumnName.GymColumName.COLUMN_NAME_GYM_ID;
import static hu.unideb.fitbase.commons.pojo.table.ColumnName.PassColumName.*;
import static hu.unideb.fitbase.commons.pojo.table.ColumnName.ReferencedColumName.REFERENCED_COLUM_NAME_ID;
import static hu.unideb.fitbase.commons.pojo.table.ColumnName.UserColumName.COLUMN_NAME_USER_ID;
import static hu.unideb.fitbase.commons.pojo.table.TableName.*;
import static javax.persistence.EnumType.STRING;

/**
 * PassEntity which represents the pass.
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = TABLE_NAME_PASS)
public class PassEntity extends BaseEntity<Long> {

    private static final long serialVersionUID = 6743106374580977116L;

    /**
     * The name of the pass.
     */
    @Column(name = COLUMN_NAME_NAME)
    private String name;

    /**
     * The price of the pass.
     */
    @Column(name = COLUMN_NAME_PRICE)
    private Integer price;

    /**
     * The type of the pass.
     */
    @Column(name = COLUMN_NAME_PASS_TYPE)
    private String passType;

    /**
     * The duration of the pass.
     */
    @Column(name = COLUMN_NAME_DURATION)
    private Integer duration;

    /**
     * The time duration of the pass.
     */
    @Column(name = COLUMN_NAME_TIME_DURATION)
    private Integer timeDuration;

    @Column(name = "pass_duration_type")
    private String passTimeDurationTypeEntity;

    /**
     * Available of the pass.
     */
    @Column(name = COLUMN_NAME_AVAILABLE)
    private Boolean available;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name = TABLE_NAME_CUSTOMER_HAS_PASS,
            joinColumns = @JoinColumn(name = COULMN_NAME_PASS_ID, referencedColumnName = REFERENCED_COLUM_NAME_ID),
            inverseJoinColumns = @JoinColumn(name = COLUMN_NAME_CUSTOMER_ID, referencedColumnName = REFERENCED_COLUM_NAME_ID))
    private List<CustomerEntity> customerEntityList;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = TABLE_NAME_PASS_HAS_GYM,
            joinColumns = @JoinColumn(name = COULMN_NAME_PASS_ID, referencedColumnName = REFERENCED_COLUM_NAME_ID),
            inverseJoinColumns = @JoinColumn(name = COLUMN_NAME_GYM_ID, referencedColumnName = REFERENCED_COLUM_NAME_ID))
    private List<GymEntity> gymEntities;

    /**
     * Builder pattern for creating pass.
     */
    @Builder
    public PassEntity(Long id,String name, Integer price, String passType, Integer duration, Integer timeDuration,String passTimeDurationType, boolean available, List<GymEntity> gymEntities){
        super(id);
        this.name = name;
        this.price = price;
        this.passType = passType;
        this.duration = duration;
        this.timeDuration = timeDuration;
        this.passTimeDurationTypeEntity = passTimeDurationType;
        this.available = available;
        this.gymEntities = gymEntities;
    }
}