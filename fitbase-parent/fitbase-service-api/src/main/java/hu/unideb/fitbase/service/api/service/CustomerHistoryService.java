package hu.unideb.fitbase.service.api.service;

import hu.unideb.fitbase.commons.pojo.exceptions.BaseException;
import hu.unideb.fitbase.service.api.domain.CustomerHistory;

public interface CustomerHistoryService {

    CustomerHistory addCustomerHistory(CustomerHistory customerHistory) throws BaseException;
}
