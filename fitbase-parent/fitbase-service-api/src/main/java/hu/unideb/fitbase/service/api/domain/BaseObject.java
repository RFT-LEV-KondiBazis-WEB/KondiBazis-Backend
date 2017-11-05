package hu.unideb.fitbase.service.api.domain;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * BaseObject.
 * @param <T> the type of the id.
 */
@Data
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class BaseObject<T> {

  /**
   * Id of the util.
   */
  protected T id;
}
