package hu.unideb.fitbase.persistence.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

import static hu.unideb.fitbase.commons.pojo.table.ColumnName.CustomerHistoryColumnName.COLUMN_NAME_ENDDATE;
import static hu.unideb.fitbase.commons.pojo.table.ColumnName.CustomerHistoryColumnName.COLUMN_NAME_STARTDATE;
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

	// customer id

	/**
     * Customers pass start date.
     */
    @Column(name = COLUMN_NAME_STARTDATE)
    private LocalDate passStartDate;

    /**
     * Customers pass end date.
     */
    @Column(name = COLUMN_NAME_ENDDATE)
    private LocalDate passEndDate;

    private LocalDate passBuyDate;

    private boolean status;

    private String passName;

    private String passType;

    private Integer passPrice;

    //gymid

//
//    @Builder
//    public CustomerHistoryEntity(Long id,LocalDate passStartDate, LocalDate passEndDate) {
//        super(id);
//        this.passStartDate = passStartDate;
//        this.passEndDate = passEndDate;
//    }
}
