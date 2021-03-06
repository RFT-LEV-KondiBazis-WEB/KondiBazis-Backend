package hu.unideb.fitbase.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

import static hu.unideb.fitbase.commons.pojo.table.ColumnName.GymColumName.*;
import static hu.unideb.fitbase.commons.pojo.table.ColumnName.PassColumName.COULMN_NAME_PASS_ID;
import static hu.unideb.fitbase.commons.pojo.table.ColumnName.ReferencedColumName.REFERENCED_COLUM_NAME_ID;
import static hu.unideb.fitbase.commons.pojo.table.ColumnName.UserColumName.COLUMN_NAME_USER_ID;
import static hu.unideb.fitbase.commons.pojo.table.TableName.*;
import static javax.persistence.CascadeType.ALL;

/**
 * GymEntity which represents the gym.
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = TABLE_NAME_GYM)

public class GymEntity extends BaseEntity<Long> {

    private static final long serialVersionUID = 4928959505728921672L;

    /**
     * The name of the gym.
     */
    @Column(name = COLUMN_NAME_GYM_NAME)
    private String name;

    /**
     * The city of the gym.
     */
    @Column(name = COLUMN_NAME_GYM_CITY)
    private String city;

    /**
     * The address of the gym.
     */
    @Column(name = COLUMN_NAME_GYM_ADDRESS)
    private String address;

    /**
     * The zip code of the gym.
     */
    @Column(name = COLUMN_NAME_GYM_ZIPCODE)
    private Integer zipCode;

    /**
     * Description of the gym.
     */
    @Column(name = COLUMN_NAME_GYM_DESCRIPTION)
    private String description;

    /**
     * Opening hours of the gym.
     */
    @Column(name = COLUMN_NAME_GYM_OPENINGHOURS)
    private String openingHours;

    /**
     * Users of the gym.
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = TABLE_NAME_USER_HAS_GYM,
            joinColumns = @JoinColumn(name = COLUMN_NAME_GYM_ID, referencedColumnName = REFERENCED_COLUM_NAME_ID),
            inverseJoinColumns = @JoinColumn(name = COLUMN_NAME_USER_ID, referencedColumnName = REFERENCED_COLUM_NAME_ID))
    private List<UserEntity> users;

    /**
     * Passes of the gym.
     */
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = TABLE_NAME_GYM_HAS_PASS,
            joinColumns = @JoinColumn(name = COLUMN_NAME_GYM_ID, referencedColumnName = REFERENCED_COLUM_NAME_ID),
            inverseJoinColumns = @JoinColumn(name = COULMN_NAME_PASS_ID, referencedColumnName = REFERENCED_COLUM_NAME_ID))
    @OrderColumn
    private List<PassEntity> passes;

    @OneToMany(cascade = ALL, mappedBy = "gymEntity")
    private List<CustomerHistoryEntity> customerHistoryEntities;

    /**
     * Builder pattern for creating gym.
     */
    @Builder
    public GymEntity(Long id, String name, String city, String address, Integer zipCode, String description,
                     String openingHours, List<UserEntity> userEntities, List<PassEntity> passEntities, List<CustomerHistoryEntity> customerHistoryEntities) {
        super(id);
        this.name = name;
        this.city = city;
        this.address = address;
        this.zipCode = zipCode;
        this.description = description;
        this.openingHours = openingHours;
        this.users = userEntities;
        this.passes = passEntities;
        this.customerHistoryEntities = customerHistoryEntities;
    }
}
