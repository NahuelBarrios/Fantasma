package com.fantasma.service;

import com.fantasma.domain.User;
import com.fantasma.dtos.JwtDTO;
import com.fantasma.dtos.UserDTO;
import com.fantasma.exceptions.FantasmaRequestException;
import com.fantasma.mapper.RoleMapper;
import com.fantasma.mapper.UserMapper;
import com.fantasma.repository.RoleRepository;
import com.fantasma.repository.UserRepository;
import com.fantasma.repository.models.RoleModel;
import com.fantasma.repository.models.UserModel;
import com.fantasma.security.JwtProvider;
import com.fantasma.security.MainUser;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder,
                       JwtProvider jwtProvider, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
        this.authenticationManager = authenticationManager;
    }

    @Transactional
    public UserDTO registerUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new FantasmaRequestException("El email ya existe", "bad.request", HttpStatus.BAD_REQUEST);
        }
        RoleModel roleModel = roleRepository.findByName("ADMIN");
        user.setRole(RoleMapper.mapModelToDomain(roleModel));
        UserModel userModel = UserMapper.mapDomainToModel(user);
        userModel.setPassword(encryptPassword(user));
        UserModel save = userRepository.save(userModel);
        User userDomain = UserMapper.mapModelToDomain(save);
        return UserMapper.mapDomainToDTO(userDomain);
    }

    private String encryptPassword(User user) {
        String password = user.getPassword();
        String encryptedPassword = passwordEncoder.encode(password);
        return encryptedPassword;
    }

    @Transactional
    public User loginUser(User user) throws FantasmaRequestException {
        if (userRepository.existsByEmail(user.getEmail())) {
            String password = user.getPassword();
            UserModel userModel = userRepository.findByEmail(user.getEmail());
            return getUserPasswordChecked(password, userModel);
        } else {
            throw new FantasmaRequestException("Wrong username or password", "invalid.access", HttpStatus.UNAUTHORIZED);
        }
    }

    private User getUserPasswordChecked(String password, UserModel userModel) throws FantasmaRequestException {
        if (passwordMatches(password, userModel.getPassword())) {
            User userDomain = UserMapper.mapModelToDomain(userModel);
            return userDomain;
        } else {
            throw new FantasmaRequestException("Wrong username or password", "invalid.access", HttpStatus.UNAUTHORIZED);
        }
    }

    private Boolean passwordMatches(String password, String passwordEncrypted) {
        return passwordEncoder.matches(password, passwordEncrypted);
    }

    public JwtDTO generateAuthenticationToken(User userDomain) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDomain.getEmail(), userDomain.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        MainUser userLog = (MainUser) authentication.getPrincipal();
        return new JwtDTO(jwt, userLog.getEmail());
    }
}