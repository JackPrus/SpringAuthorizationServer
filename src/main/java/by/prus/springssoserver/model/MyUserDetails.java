package by.prus.springssoserver.model;

import by.prus.springssoserver.entity.CustomUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public class MyUserDetails implements UserDetails {

    private static final long serialVersionUID = 5343478654L;
    private final CustomUser customUser;

    public MyUserDetails(CustomUser customUser) {
        this.customUser = customUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return customUser.getAuthorities().stream()
                .map(auth -> new SimpleGrantedAuthority(auth.getAuthority()))
                .collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return "{noop}"+customUser.getPassword();
    }

    @Override
    public String getUsername() {
        return customUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return customUser.getAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return customUser.getAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return customUser.getCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return customUser.getEnabled();
    }
}
