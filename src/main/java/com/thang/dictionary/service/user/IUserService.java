package com.thang.dictionary.service.user;

import com.thang.dictionary.model.entity.auth.User;
import com.thang.dictionary.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface IUserService extends IGeneralService<User>, UserDetailsService {

    User saveAdmin(User user);

    User saveUser(User user);

    Optional<User> findByUsername(String username);

    Optional<User> findUsersByEmail(String email);

    Optional<User> findUsersByPhone(String phone);

}
