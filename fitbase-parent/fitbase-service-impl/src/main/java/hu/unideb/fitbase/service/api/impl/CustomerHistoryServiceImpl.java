package hu.unideb.fitbase.service.api.impl;

import hu.unideb.fitbase.commons.pojo.exceptions.BaseException;
import hu.unideb.fitbase.persistence.entity.CustomerHistoryEntity;
import hu.unideb.fitbase.persistence.repository.CustomerHistoryRepository;
import hu.unideb.fitbase.service.api.converter.CustomerHistroyEntityToCustomerHistroyListConverter;
import hu.unideb.fitbase.service.api.domain.CustomerHistory;
import hu.unideb.fitbase.service.api.service.CustomerHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CustomerHistoryServiceImpl implements CustomerHistoryService {

    @Autowired
    private CustomerHistoryRepository customerHistoryRepository;

    @Autowired
    private ConversionService conversionService;

    @Autowired
    private CustomerHistroyEntityToCustomerHistroyListConverter customerHistroyEntityToCustomerHistroyListConverter;

    @Override
    public CustomerHistory addCustomerHistory(CustomerHistory customerHistory) throws BaseException {
        log.trace(">> addCustomerHistory: [customerHistory:{}]", customerHistory);
        CustomerHistory convert = conversionService.convert(customerHistoryRepository.save(conversionService.convert(customerHistory, CustomerHistoryEntity.class)), CustomerHistory.class);
        log.trace("<< addCustomerHistory: [customerHistory:{}]", customerHistory);
        return convert;
    }

    @Override
    public List<CustomerHistory> getCustomerHistory(Long id) {
        List<CustomerHistoryEntity> byCustomerEntityId = customerHistoryRepository.findByCustomerEntityId(id);
        return customerHistroyEntityToCustomerHistroyListConverter.convert(byCustomerEntityId);
    }
}