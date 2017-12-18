package hu.unideb.fitbase.service.api.domain;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

/**
 * Gym Statistics domain class.
 */

@Data
@Builder

public class GymStatistics implements Serializable{

	private static final long serialVersionUID = -1574080849764266384L;

	private Double countPassesOnSysdateByGym;
	
	private Double sumCostOfPassesOnSysdateByGym;
	
	private Double sumCustomersOnSysdateByGym;
}
