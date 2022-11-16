package edu.azati.shop.services;

import edu.azati.shop.dto.UserDto;
import edu.azati.shop.entity.User;
import edu.azati.shop.enums.UserRole;
import edu.azati.shop.error.UserAlreadyExistException;
import edu.azati.shop.repository.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public void addUser(User user) {
        userRepo.save(user);
        LOG.info("Added user to table with name = {}", user.getName());
    }

    public void addUsers(List<User> users) {
        userRepo.saveAll(users);
    }

    public List<User> getAllUsers() {
        return StreamSupport.stream(userRepo.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public User getUserById(long id) {
        return userRepo.findUserById(id);
    }

    public User getUserByName(String name) {
        return userRepo.findUserByName(name);
    }

    public User getUserBySurname(String surname) {
        return userRepo.findUserBySurname(surname);
    }

    public void deleteUserById(long id) {
        userRepo.deleteById(id);
        LOG.info("Removed user with id = {}", id);
    }

    public void updateUser(long id, String name, String surname, String patronynic, UserRole userRole) {
        User user = getUserById(id);
        user.setSurname(surname);
        user.setName(name);
        user.setPatronymic(patronynic);
        user.setUserRole(userRole);
        userRepo.save(user);
        userRepo.deleteById(id);
        LOG.info("Updated user with id = {}", id);
    }

    public User registerNewUserAccount(UserDto userDto) throws UserAlreadyExistException {
        if (emailExists(userDto.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email address: "
                    + userDto.getEmail());
        }
        User user = new User();
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        user.setPassword(encodedPassword);
        user.setEmail(userDto.getEmail());
        user.setUserRole(UserRole.Customer);

        return userRepo.save(user);
    }

    private boolean emailExists(String email) {
        return userRepo.findByEmail(email) != null;
    }
}
