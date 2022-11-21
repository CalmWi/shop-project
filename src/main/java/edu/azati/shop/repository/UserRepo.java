package edu.azati.shop.repository;

import edu.azati.shop.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {
    User findUserById(long id);

    User findUserByName(String name);

    User findUserBySurname(String surname);

    void deleteById(long id);

    User findByEmail(String email);
}
