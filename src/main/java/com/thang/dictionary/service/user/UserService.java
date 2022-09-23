package com.thang.dictionary.service.user;

import com.thang.dictionary.model.dto.auth.UserPrincipal;
import com.thang.dictionary.model.entity.auth.Role;
import com.thang.dictionary.model.entity.auth.User;
import com.thang.dictionary.repository.IRoleRepository;
import com.thang.dictionary.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sun.security.util.Password;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Iterable<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return this.userRepository.findById(id);
    }

    @Override
    public User save(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        this.userRepository.deleteById(id);
    }

    @Override
    public User saveAdmin(User user) {
        Optional<Role> roleOptional = this.roleRepository.findRolesByName("ROLE_ADMIN");
        List<Role> roles = new ArrayList<>();
        roles.add(new Role(roleOptional.get().getId(), roleOptional.get().getName()));
        String password = user.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);
        user.setRoles(roles);
        user.setAvatar("default-avatar.jpg");
        user.setEmail("admin@gmail.com");
        user.setPhone("0394910426");
        return userRepository.save(user);
    }

    @Override
    public User saveUser(User user) {
        Optional<Role> roleOptional = this.roleRepository.findRolesByName("ROLE_USER");
        List<Role> roles = new ArrayList<>();
        roles.add(new Role(roleOptional.get().getId(), roleOptional.get().getName()));
        String password = user.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);
        user.setRoles(roles);
        user.setAvatar("default-avatar.jpg");
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findUsersByEmail(String email) {
        return this.userRepository.findUsersByEmail(email);
    }

    @Override
    public Optional<User> findUsersByPhone(String phone) {
        return this.userRepository.findUsersByPhone(phone);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).get();
        return UserPrincipal.build(user);
    }
}
