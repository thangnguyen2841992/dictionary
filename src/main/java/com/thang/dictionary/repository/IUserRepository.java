package com.thang.dictionary.repository;
import com.thang.dictionary.model.entity.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findUsersByEmail(String email);

    Optional<User> findUsersByPhone(String phone);
}
