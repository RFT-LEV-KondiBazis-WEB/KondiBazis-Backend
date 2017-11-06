package hu.unideb.fitbase.service.api.impl;

import hu.unideb.fitbase.commons.pojo.response.Data;
import hu.unideb.fitbase.persistence.entity.UserEntity;
import hu.unideb.fitbase.persistence.repository.UserRepository;
import hu.unideb.fitbase.service.api.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConversionService conversionService;

    @Override
    public Data findByUsername(String username) {
        log.trace(">> findByUsername: [username:{}]", username);
        UserEntity userEntity = userRepository.findByUsername(username);
        Data convert = conversionService.convert(userEntity, Data.class);
        log.trace("<< findByUsername: [username:{}]", username);
        return convert;
    }

    @Override
    public Data save(Data user) {
        log.trace(">> save: [user:{}]", user);
        Data convert = conversionService.convert(userRepository.save(conversionService.convert(user, UserEntity.class)), Data.class);
        log.trace("<< save: [user:{}]", user);
        return convert;
    }

    @Override
    public Data findById(Long id) {
        log.trace(">> findById: [id:{}]", id);
        Data result;
        UserEntity userEntity = userRepository.findOne(id);
        if (userEntity == null) {
            result = null;
        } else {
            result = conversionService.convert(userEntity, Data.class);
        }
        log.trace("<< findById: [id:{}]", id);
        return result;
    }

    @Override
    public Data findByEmail(String email) {
        log.trace(">> findByEmail: [email:{}]", email);
        Data user = conversionService.convert(userRepository.findByEmail(email), Data.class);
        log.trace("<< findByEmail: [email:{}]", email);
        return user;
    }

    @Override
    public List<Data> getAllUsers() {
        log.trace(">> getAllUsers");
        List<UserEntity> users = userRepository.findAll();
        List<Data> userList = users.stream()
                .map(entity -> conversionService.convert(entity, Data.class))
                .collect(Collectors.toList());
        log.trace("<< getAllUsers");
        return userList;
    }

    @Override
    public boolean containsAny() {
        return userRepository.anyExists();
    }
}
