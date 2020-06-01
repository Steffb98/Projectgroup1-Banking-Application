package io.swagger.dao;

import io.swagger.model.Users;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends CrudRepository<Users, Long> {


    @Query("SELECT u FROM Users u WHERE u.email = ?1 and u.password = ?2")
    Users findUserByEmailAndPassword(String email, String password);

    Users findByEmail(String email);


    @Query("SELECT u FROM Users u WHERE u.firstname LIKE ?1 OR u.lastname LIKE ?1")
    Iterable<Users> GetUserByName(String name);
}
