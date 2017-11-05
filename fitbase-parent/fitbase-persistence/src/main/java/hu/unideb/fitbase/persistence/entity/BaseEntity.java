package hu.unideb.fitbase.persistence.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Base entity which provides the primary key.
 *
 * @param <T> the type of the primary key.
 */
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@MappedSuperclass
public class BaseEntity<T extends Serializable> implements Serializable {

    /**
     * Id as primary key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected T id;
}