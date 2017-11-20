package hu.unideb.fitbase.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

import static hu.unideb.fitbase.commons.pojo.table.ColumnName.CustomerColumName.COLUMN_NAME_CUSTOMER_ID;
import static hu.unideb.fitbase.commons.pojo.table.ColumnName.GymColumName.*;
import static hu.unideb.fitbase.commons.pojo.table.ColumnName.ReferencedColumName.REFERENCED_COLUM_NAME_ID;
import static hu.unideb.fitbase.commons.pojo.table.ColumnName.UserColumName.COLUMN_NAME_USER_ID;
import static hu.unideb.fitbase.commons.pojo.table.TableName.*;

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
     * Customers of the gym.
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = TABLE_NAME_CUSTOMER_HISTORY,
            joinColumns = @JoinColumn(name = COLUMN_NAME_GYM_ID, referencedColumnName = REFERENCED_COLUM_NAME_ID),
            inverseJoinColumns = @JoinColumn(name = COLUMN_NAME_CUSTOMER_ID, referencedColumnName = REFERENCED_COLUM_NAME_ID))
    private List<CustomerEntity> customers;

    /**
     * Users of the gym.
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = TABLE_NAME_USER_HAS_GYM,
            joinColumns = @JoinColumn(name = COLUMN_NAME_GYM_ID, referencedColumnName = REFERENCED_COLUM_NAME_ID),
            inverseJoinColumns = @JoinColumn(name = COLUMN_NAME_USER_ID, referencedColumnName = REFERENCED_COLUM_NAME_ID))
    private List<UserEntity> users;


    // gym has pass tábla

    /**
     * Builder pattern for creating gym.
     */
    @Builder
    public GymEntity(Long id, String name, String city, String address, Integer zipCode, String description, String openingHours, List<UserEntity> userEntities) {
        super(id);
        this.name = name;
        this.city = city;
        this.address = address;
        this.zipCode = zipCode;
        this.description = description;
        this.openingHours = openingHours;
        this.users = userEntities;
    }
}
