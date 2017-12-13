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
public class GymEntityToGymConverter implements Converter<GymEntity, Gym> {

    @Autowired
    private UserEntityToUserConverter userEntityToUserConverter;

    @Autowired
    private PassEntityToPassConverter passEntityToPassConverter;

    @Autowired
    private CustomerEntityToCustomerConverter customerEntityToCustomerConverter;

    @Override
    public Gym convert(GymEntity source) {
        return Gym.builder()
                .id(source.getId())
                .name(source.getName())
                .city(source.getCity())
                .address(source.getAddress())
                .zipCode(String.valueOf(source.getZipCode()))
                .description(source.getDescription())
                .openingHours(source.getOpeningHours())
                .userList(convert(source.getUsers()))
                .passes(convert1(source.getPasses()))
                .customers(convert2(source.getCustomers()))
                .build();
    }

    private List<User> convert(List<UserEntity> source) {
        return source.stream().map(user -> userEntityToUserConverter.convert(user)).collect(Collectors.toList());
    }

    private List<Pass> convert1(List<PassEntity> source) {
        return source.stream().map(pass -> passEntityToPassConverter.convert(pass)).collect(Collectors.toList());
    }

    private List<Customer> convert2(List<CustomerEntity> source) {
        return source.stream().map(cust -> customerEntityToCustomerConverter.convert(cust)).collect(Collectors.toList());
    }

}
