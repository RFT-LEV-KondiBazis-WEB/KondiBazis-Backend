package hu.unideb.fitbase.service.api.impl;

import hu.unideb.fitbase.commons.pojo.response.User;
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
    public User findByUsername(String username) {
        log.trace(">> findByUsername: [username:{}]", username);
        UserEntity userEntity = userRepository.findByUsername(username);
        User convert = conversionService.convert(userEntity, User.class);
        log.trace("<< findByUsername: [username:{}]", username);
        return convert;
    }

    @Override
    public User save(User user) {
        log.trace(">> save: [user:{}]", user);
        User convert = conversionService.convert(userRepository.save(conversionService.convert(user, UserEntity.class)), User.class);
        log.trace("<< save: [user:{}]", user);
        return convert;
    }

    @Override
    public User findById(Long id) {
        log.trace(">> findById: [id:{}]", id);
        User result;
        UserEntity userEntity = userRepository.findOne(id);
        if (userEntity == null) {
            result = null;
        } else {
            result = conversionService.convert(userEntity, User.class);
        }
        log.trace("<< findById: [id:{}]", id);
        return result;
    }

    @Override
    public User findByEmail(String email) {
        log.trace(">> findByEmail: [email:{}]", email);
        User user = conversionService.convert(userRepository.findByEmail(email), User.class);
        log.trace("<< findByEmail: [email:{}]", email);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        log.trace(">> getAllUsers");
        List<UserEntity> users = userRepository.findAll();
        List<User> userList = users.stream()
                .map(entity -> conversionService.convert(entity, User.class))
                .collect(Collectors.toList());
        log.trace("<< getAllUsers");
        return userList;
    }

    @Override
    public boolean containsAny() {
        return userRepository.anyExists();
    }
}
