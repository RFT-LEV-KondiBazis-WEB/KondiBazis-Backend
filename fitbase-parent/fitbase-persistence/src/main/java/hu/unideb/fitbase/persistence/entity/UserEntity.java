package hu.unideb.fitbase.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

import static hu.unideb.fitbase.commons.pojo.exclusion.FieldExclusion.EXCLUDE_PASSWORD;
import static hu.unideb.fitbase.commons.pojo.table.ColumnName.UserColumName.*;
import static hu.unideb.fitbase.commons.pojo.table.TableName.TABLE_NAME_USER;
import static javax.persistence.EnumType.STRING;

/**
 * UserEntity which represents the user.
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, exclude = EXCLUDE_PASSWORD)
@Entity
@Table(name = TABLE_NAME_USER, uniqueConstraints = @UniqueConstraint(columnNames = COLUMN_NAME_USERNAME))
public class UserEntity extends BaseEntity<Long> {

	private static final long serialVersionUID = 9097446533475236308L;

	/**
     * The username of the user.
     */
    @Column(name = COLUMN_NAME_USERNAME)
    private String username;

    /**
     * The email of the user.
     */
    @Column(name = COLUMN_NAME_EMAIL)
    private String email;

    /**
     * The password of the user.
     */
    @Column(name = COLUMN_NAME_PASSWORD)
    private String password;

    /**
     * First name of the user.
     */
    @Column(name = COLUMN_NAME_FIRST_NAME)
    private String firstName;

    /**
     * Last name of the user.
     */
    @Column(name = COLUMN_NAME_LAST_NAME)
    private String lastName;

    /**
     * Remember token of the user.
     */
    @Column(name = COLUMN_NAME_REMEMBER_TOKEN)
    private String rememberToken;

    /**
     * User created date.
     */
    @Column(name = COLUMN_NAME_CREATED_DATE)
    private LocalDate createdDate;

    /**
     * User role.
     */
    @Column(name = COLUMN_NAME_ROLE)
    @Enumerated(value = STRING)
    private UserRoleEntity userRole;

    /**
     * Gyms of the user.
     */
    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY)
    private List<GymEntity> gyms;

    /**
     * Builder pattern for creating user.
     */
    @Builder
    public UserEntity(Long id, String username, String email, String password, String firstName, String lastName, String rememberToken, LocalDate createdDate, UserRoleEntity userRoleEntity ) {
        super(id);
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.rememberToken = rememberToken;
        this.createdDate = createdDate;
        this.userRole = userRoleEntity;
    }
}
