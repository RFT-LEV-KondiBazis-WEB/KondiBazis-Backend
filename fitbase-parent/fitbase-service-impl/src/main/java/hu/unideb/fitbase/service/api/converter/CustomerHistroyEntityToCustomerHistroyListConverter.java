package hu.unideb.fitbase.service.api.converter;

import hu.unideb.fitbase.persistence.entity.CustomerHistoryEntity;
import hu.unideb.fitbase.service.api.domain.CustomerHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerHistroyEntityToCustomerHistroyListConverter implements Converter<List<CustomerHistoryEntity>, List<CustomerHistory>> {

    @Autowired
    private CustomerHistoryEntityToCustomerHistoryConverter customerHistoryEntityToCustomerHistoryConverter;

    @Override
    public List<CustomerHistory> convert(List<CustomerHistoryEntity> source) {
        return source.stream().map(customer -> customerHistoryEntityToCustomerHistoryConverter.convert(customer)).collect(Collectors.toList());
    }
}