package com.fantasma.security;

import com.fantasma.repository.models.UserModel;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@AllArgsConstructor
public class MainUser implements UserDetails {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    private LocalDateTime creationDate;

    public static MainUser build(UserModel userModel) {
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority((userModel.getRole()
                .getName())));
        return new MainUser(userModel.getId(), userModel.getFirstName(), userModel.getLastName()
                , userModel.getEmail(), userModel.getPassword(),
                 authorities, userModel.getCreationDate());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}