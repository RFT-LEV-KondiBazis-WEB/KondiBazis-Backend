package hu.unideb.fitbase.service.api.service;

import hu.unideb.fitbase.service.api.domain.User;

import java.util.List;

/**
 * User service to handle user interactions.
 */
public interface UserService {

    /**
     * Finds a user by his/her user name.
     *
     * @param username the user name of the user
     * @return the user with the given user name
     */
    User findByUsername(String username);

    /**
     * Save user.
     *
     * @param user user object to be saved.
     * @return saved user object.
     */
    User save(User user);

    /**
     * Finds a user by its database id
     *
     * @param id the id of the user
     * @return the user with the given id
     */
    User findById(Long id);

    /**
     * Finds a user by its email.
     *
     * @param email user's email.
     * @return user domain object.
     */
    User findByEmail(String email);

    /**
     * List all user.
     *
     * @return all users in the system.
     */
    List<User> getAllUsers();

    /**
     * Checks if any user exist in the system.
     *
     * @return if any user exists
     */

    boolean containsAny();
}
