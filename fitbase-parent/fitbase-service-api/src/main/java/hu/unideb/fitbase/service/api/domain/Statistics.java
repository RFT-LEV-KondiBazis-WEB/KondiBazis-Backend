package hu.unideb.fitbase.service.api.domain;

import java.io.Serializable;
import lombok.Builder;
import lombok.Data;

/**
 * Statistics domain class.
 */

@Data
@Builder

public class Statistics implements Serializable{

	private static final long serialVersionUID = -8141994966820652975L;

	private Long countOfGyms;
	
	private Long countOfPasses;
	
	private Long countOfCustomers;
	
	
	
}
