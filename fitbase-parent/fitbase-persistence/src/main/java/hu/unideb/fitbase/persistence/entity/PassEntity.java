package hu.unideb.fitbase.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

import static hu.unideb.fitbase.commons.pojo.table.ColumnName.CustomerColumName.COLUMN_NAME_CUSTOMER_ID;
import static hu.unideb.fitbase.commons.pojo.table.ColumnName.GymColumName.COLUMN_NAME_GYM_ID;
import static hu.unideb.fitbase.commons.pojo.table.ColumnName.PassColumName.*;
import static hu.unideb.fitbase.commons.pojo.table.ColumnName.ReferencedColumName.REFERENCED_COLUM_NAME_ID;
import static hu.unideb.fitbase.commons.pojo.table.ColumnName.UserColumName.COLUMN_NAME_USER_ID;
import static hu.unideb.fitbase.commons.pojo.table.TableName.*;

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
     * Pass is limited.
     */
    @Column(name = COLUMN_NAME_IS_LIMITED)
    private Boolean isLimited;

    /**
     * The limit of the pass.
     */
    @Column(name = COLUMN_NAME_LIMIT)
    private Integer limitNumber;

    /**
     * The duration of the pass.
     */
    @Column(name = COLUMN_NAME_DURATION)
    private Integer duration;

    /**
     * The price of the pass.
     */
    @Column(name = COLUMN_NAME_PRICE)
    private Integer price;

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
    @JoinTable(name = TABLE_NAME_USER_HAS_PASS,
            joinColumns = @JoinColumn(name = COLUMN_NAME_USER_ID, referencedColumnName = REFERENCED_COLUM_NAME_ID),
            inverseJoinColumns = @JoinColumn(name = COULMN_NAME_PASS_ID, referencedColumnName = REFERENCED_COLUM_NAME_ID))
    private List<UserEntity> users;

    /**
     * Builder pattern for creating pass.
     */
    @Builder
    public PassEntity(Long id, String name, Boolean isLimited, Integer limitNumber, Integer duration, Integer price, Boolean available, List<UserEntity> userEntities) {
        super(id);
        this.name = name;
        this.isLimited = isLimited;
        this.limitNumber = limitNumber;
        this.duration = duration;
        this.price = price;
        this.available = available;
        this.users = userEntities;
    }
}
