package hu.unideb.fitbase.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

import static hu.unideb.fitbase.commons.pojo.table.ColumnName.PassColumName.*;
import static hu.unideb.fitbase.commons.pojo.table.TableName.TABLE_NAME_PASS;

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
    @JoinTable(name = "customer_has_pass",
            joinColumns = @JoinColumn(name = "pass_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "id"))
    private List<CustomerEntity> customerEntityList;

    /**
     * Builder pattern for creating pass.
     */
    @Builder
    public PassEntity(Long id, String name, Boolean isLimited, Integer limitNumber, Integer duration, Integer price, Boolean available) {
        super(id);
        this.name = name;
        this.isLimited = isLimited;
        this.limitNumber = limitNumber;
        this.duration = duration;
        this.price = price;
        this.available = available;
    }
}
