package hu.unideb.fitbase.service.api.converter;

import hu.unideb.fitbase.persistence.entity.CustomerEntity;
import hu.unideb.fitbase.persistence.entity.GymEntity;
import hu.unideb.fitbase.persistence.entity.PassEntity;
import hu.unideb.fitbase.persistence.entity.UserEntity;
import hu.unideb.fitbase.service.api.domain.Customer;
import hu.unideb.fitbase.service.api.domain.Gym;
import hu.unideb.fitbase.service.api.domain.Pass;
import hu.unideb.fitbase.service.api.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GymToGymEntityConverter implements Converter<Gym, GymEntity> {

    @Autowired
    private UserToUserEntityConverter userToUserEntityConverter;

    @Autowired
    private PassToPassEntityConverter passToPassEntityConverter;

    @Autowired
    private CustomerToCustomerEntityConverter customerToCustomerEntityConverter;

    @Override
    public GymEntity convert(Gym source) {
        return GymEntity.builder()
                .id(source.getId())
                .name(source.getName())
                .city(source.getCity())
                .address(source.getAddress())
                .zipCode(Integer.parseInt(source.getZipCode()))
                .description(source.getDescription())
                .openingHours(source.getOpeningHours())
                .userEntities(convert(source.getUserList()))
                .passEntities(convert1(source.getPasses()))
                .customerEntities(convert2(source.getCustomers()))
                .build();
    }

    private List<UserEntity> convert(List<User> source) {
        return source.stream().map(userEntity -> userToUserEntityConverter.convert(userEntity)).collect(Collectors.toList());
    }

    private List<PassEntity> convert1(List<Pass> source) {
        return source.stream().map(passEntity -> passToPassEntityConverter.convert(passEntity)).collect(Collectors.toList());
    }

    private List<CustomerEntity> convert2(List<Customer> source) {
        return source.stream().map(customerEntity -> customerToCustomerEntityConverter.convert(customerEntity)).collect(Collectors.toList());
    }

}
