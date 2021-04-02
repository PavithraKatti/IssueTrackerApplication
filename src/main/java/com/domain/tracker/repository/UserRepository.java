package com.domain.tracker.repository;

import com.domain.tracker.model.Issue;
import com.domain.tracker.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    User findUserById(int id);

    Optional<User> findByUsername(String username);
}
