package hu.unideb.fitbase.service.api.converter;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import hu.unideb.fitbase.persistence.entity.CustomerEntity;
import hu.unideb.fitbase.persistence.entity.CustomerHistoryEntity;
import hu.unideb.fitbase.persistence.entity.GymEntity;
import hu.unideb.fitbase.service.api.domain.Customer;
import hu.unideb.fitbase.service.api.domain.CustomerHistory;
import hu.unideb.fitbase.service.api.domain.Gym;

@Component
public class CustomerHistoryEntityToCustomerHistoryConverter implements Converter<CustomerHistoryEntity, CustomerHistory> {

    @Autowired
    private CustomerEntityToCustomerConverter customerEntityToCustomerConverter;

    @Autowired
    private GymEntityToGymConverter gymEntityToGymConverter;

    @Override
    public CustomerHistory convert(CustomerHistoryEntity customerHistoryEntity) {
        return CustomerHistory.builder()
                .passStartDate(customerHistoryEntity.getPassStartDate())
                .passEndDate(customerHistoryEntity.getPassEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                .passBuyDate(customerHistoryEntity.getPassBuyDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                .status(customerHistoryEntity.isStatus())
                .passName(customerHistoryEntity.getPassName())
                .passType(customerHistoryEntity.getPassType())
                .passPrice(customerHistoryEntity.getPassPrice())
                .passAllDuration(customerHistoryEntity.getPassAllDuration())
                .passDurationLeft(customerHistoryEntity.getPassDurationLeft())
                .customer(customerEntityToCustomer(customerHistoryEntity.getCustomerEntity()))
                .gym(gymEntityToGym(customerHistoryEntity.getGymEntity()))
                .build();
    }

    private Customer customerEntityToCustomer(CustomerEntity source) {
        return customerEntityToCustomerConverter.convert(source);
    }

    private Gym gymEntityToGym(GymEntity source) {
        return gymEntityToGymConverter.convert(source);
    }
}
