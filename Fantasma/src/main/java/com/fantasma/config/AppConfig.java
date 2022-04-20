package com.fantasma.config;

import com.fantasma.repository.EntidadUnoRepository;
import com.fantasma.repository.RoleRepository;
import com.fantasma.repository.UserRepository;
import com.fantasma.security.JwtProvider;
import com.fantasma.security.UserDetailsServiceImpl;
import com.fantasma.service.EntidadUnoService;
import com.fantasma.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppConfig {

    @Bean
    public EntidadUnoService entidadUnoService(EntidadUnoRepository entidadUnoRepository){
        return new EntidadUnoService(entidadUnoRepository);
    }

    @Bean
    public UserDetailsServiceImpl userDetailsServiceImpl(UserRepository userRepository) {
        return new UserDetailsServiceImpl(userRepository);
    }

    @Bean
    public UserService userService(UserRepository userRepository, RoleRepository roleRepository,
                                   PasswordEncoder passwordEncoder, JwtProvider jwtProvider,
                                   AuthenticationManager authenticationManager){
        return new UserService(userRepository,roleRepository,passwordEncoder,jwtProvider,authenticationManager);
    }

    @Bean
    public JwtProvider jwtProvider(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.expiration}") int expiration) {
        return new JwtProvider(secret, expiration);
    }
}
