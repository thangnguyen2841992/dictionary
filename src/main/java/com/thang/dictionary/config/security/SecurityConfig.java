package com.thang.dictionary.config.security;

import com.thang.dictionary.config.CustomAccessDeniedHandler;
import com.thang.dictionary.config.JwtAuthenticationFilter;
import com.thang.dictionary.config.RestAuthenticationEntryPoint;
import com.thang.dictionary.model.entity.TypeSearch;
import com.thang.dictionary.model.entity.auth.Role;
import com.thang.dictionary.model.entity.auth.User;
import com.thang.dictionary.repository.IRoleRepository;
import com.thang.dictionary.repository.IUserRepository;
import com.thang.dictionary.service.role.IRoleService;
import com.thang.dictionary.service.typeSearch.ITypeSearchService;
import com.thang.dictionary.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.PostConstruct;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private ITypeSearchService typeSearchService;



    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }


    @Bean
    public PasswordEncoder passwordEncoder() { //bean m?? h??a pass
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RestAuthenticationEntryPoint restServicesEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //l???y user t??? DB
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @PostConstruct
    public void init() {
        List<User> users = userRepository.findAll();
        List<Role> roleList = roleRepository.findAll();
        Iterable<TypeSearch> typeSearches = typeSearchService.findAll();
        if (roleList.isEmpty()) {
            Role roleAdmin = new Role("ROLE_ADMIN");
            roleService.save(roleAdmin);
            Role roleUser = new Role("ROLE_USER");
            roleService.save(roleUser);

        }
        if (users.isEmpty()) {
            User admin = new User("admin", "thuthuyda1");
            userService.saveAdmin(admin);
        }
        if (!typeSearches.iterator().hasNext()) {
            this.typeSearchService.save(new TypeSearch("Nh???t -> Vi???t"));
            this.typeSearchService.save(new TypeSearch("Vi???t -> Nh???t"));
        }
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/**");
        http.httpBasic().authenticationEntryPoint(restServicesEntryPoint());//T??y ch???nh l???i th??ng b??o 401 th??ng qua class restEntryPoint
        http.authorizeRequests()
                .antMatchers("/login",
                        "/register", "/**").permitAll() // t???t c??? truy c???p ???????c
                .anyRequest().authenticated()  //c??c request c??n l???i c???n x??c th???c
                .and().csrf().disable(); // v?? hi???u h??a b???o v??? c???a csrf (ki???m so??t quy???n truy c???p)
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.cors();
    }
}