package hu.unideb.fitbase.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

import static hu.unideb.fitbase.commons.pojo.table.ColumnName.CustomerColumName.COLUMN_NAME_CUSTOMER_ID;
import static hu.unideb.fitbase.commons.pojo.table.ColumnName.CustomerHistoryColumnName.*;
import static hu.unideb.fitbase.commons.pojo.table.ColumnName.GymColumName.COLUMN_NAME_GYM_ID;
import static hu.unideb.fitbase.commons.pojo.table.TableName.TABLE_NAME_CUSTOMER_HISTORY;

/**
 * CustomHistoryEntity which represents the customer history.
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = TABLE_NAME_CUSTOMER_HISTORY)
public class CustomerHistoryEntity extends BaseEntity<Long> {

    private static final long serialVersionUID = 4663328684610888566L;

    /**
     * Customers pass start date.
     */
    @Column(name = COLUMN_NAME_STARTDATE)
    private Date passStartDate;

    /**
     * Customers pass end date.
     */
    @Column(name = COLUMN_NAME_ENDDATE)
    private Date passEndDate;

    /**
     * Customers pass buy date.
     */
    @Column(name = COLUMN_NAME_PASS_BUYDATE)
    private Date passBuyDate;

    /**
     * Customers pass status.
     */
    @Column(name = COLUMN_NAME_PASS_STATUS)
    private boolean status;

    /**
     * Customers pass name.
     */
    @Column(name = COLUMN_NAME_PASS_NAME)
    private String passName;

    /**
     * Customers pass type.
     */
    @Column(name = COLUMN_NAME_PASS_TYPE)
    private String passType;

    /**
     * Customers pass price.
     */
    @Column(name = COLUMN_NAME_PASS_PRICE)
    private Integer passPrice;

    /**
     * Customers pass duration.
     */
    @Column(name = COLUMN_NAME_PASS_ALL_DURATION)
    private Integer passAllDuration;

    /**
     * Customers pass duration left.
     */
    @Column(name = COLUMN_NAME_PASS_LEFT_DURATION)
    private Integer passDurationLeft;

    /**
     * Customer.
     */
    @ManyToOne
    @JoinColumn(name = COLUMN_NAME_CUSTOMER_ID, nullable = false)
    private CustomerEntity customerEntity;

    /**
     * Customers gym.
     */
    @ManyToOne
    @JoinColumn(name = COLUMN_NAME_GYM_ID, nullable = false)
    private GymEntity gymEntity;

    @Builder
    public CustomerHistoryEntity(Long id, Date passStartDate, Date passEndDate, Date passBuyDate, boolean status, String passName, String passType, Integer passPrice,Integer passAllDuration, Integer passDurationLeft, CustomerEntity customerEntity, GymEntity gymEntity) {
        super(id);
        this.passStartDate = passStartDate;
        this.passEndDate = passEndDate;
        this.passBuyDate = passBuyDate;
        this.status = status;
        this.passName = passName;
        this.passType = passType;
        this.passPrice = passPrice;
        this.passAllDuration = passAllDuration;
        this.passDurationLeft = passDurationLeft;
        this.customerEntity = customerEntity;
        this.gymEntity = gymEntity;
    }

}
