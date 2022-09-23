package com.thang.dictionary.repository;

import com.thang.dictionary.model.entity.auth.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findRolesByName(String name);
}
