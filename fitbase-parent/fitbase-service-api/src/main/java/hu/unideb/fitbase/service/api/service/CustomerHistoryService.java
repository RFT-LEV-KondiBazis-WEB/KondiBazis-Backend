package hu.unideb.fitbase.service.api.service;

import hu.unideb.fitbase.commons.pojo.exceptions.BaseException;
import hu.unideb.fitbase.service.api.domain.CustomerHistory;

import java.util.List;

public interface CustomerHistoryService {

    CustomerHistory addCustomerHistory(CustomerHistory customerHistory) throws BaseException;

    List<CustomerHistory> getCustomerHistory(Long id);
    
    Double countPassesOnSysdateByGym(Long id);
    
    Double sumCostOfPassesOnSysdateByGym(Long id);
    
    Double sumCustomersOnSysdateByGym(Long id);
}
