package com.thang.dictionary.controller;

import com.thang.dictionary.config.JWTProvider;
import com.thang.dictionary.model.dto.ErrorMessage;
import com.thang.dictionary.model.dto.auth.JwtResponse;
import com.thang.dictionary.model.dto.auth.LoginForm;
import com.thang.dictionary.model.dto.auth.RegisterForm;
import com.thang.dictionary.model.entity.auth.User;
import com.thang.dictionary.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@CrossOrigin("*")
public class AuthRestController {
    @Autowired
    private IUserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTProvider jwtProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginForm loginForm) {
        Optional<User> currentUser = userService.findByUsername(loginForm.getUsername());
        if (!currentUser.isPresent()) {
            return new ResponseEntity<>(new ErrorMessage("Tài khoản không tồn tại!"), HttpStatus.BAD_REQUEST);
        }
        boolean matchPassword = passwordEncoder.matches(loginForm.getPassword(), currentUser.get().getPassword());
        if (!matchPassword) {
            ErrorMessage errorMessage = new ErrorMessage("Mật khẩu không đúng");
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateTokenLogin(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponse(jwt, currentUser.get().getId(), currentUser.get().getUsername(), userDetails.getAuthorities()));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterForm registerForm) {
        boolean confirmPasswordMathch = registerForm.getConfirmPassword().equals(registerForm.getPassword());
        if (!confirmPasswordMathch) {
            return new ResponseEntity<>(new ErrorMessage("Mật khẩu không trùng khớp!"), HttpStatus.BAD_REQUEST);
        }
        Optional<User> userOptional = this.userService.findByUsername(registerForm.getUsername());
        if (userOptional.isPresent()) {
            return new ResponseEntity<>(new ErrorMessage("Tài khoản đã tồn tại!"), HttpStatus.BAD_REQUEST);
        }
        Optional<User> userOptional1 = this.userService.findUsersByEmail(registerForm.getEmail());
        if (userOptional1.isPresent()) {
            return new ResponseEntity<>(new ErrorMessage("Tài khoản đã tồn tại!"), HttpStatus.BAD_REQUEST);
        }
        Optional<User> userOptional2 = this.userService.findUsersByPhone(registerForm.getPhone());
        if (userOptional2.isPresent()) {
            return new ResponseEntity<>(new ErrorMessage("Tài khoản đã tồn tại!"), HttpStatus.BAD_REQUEST);
        }
        User newUser = new User();
        newUser.setUsername(registerForm.getUsername());
        newUser.setPassword(registerForm.getPassword());
        newUser.setPhone(registerForm.getPhone());
        newUser.setEmail(registerForm.getEmail());
        this.userService.saveUser(newUser);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
}
