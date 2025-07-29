package EasyTransfer.demo.model;

import EasyTransfer.demo.service.QRUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class userPrincipal implements UserDetails {

    @Autowired
    private QRUser user;

    @Autowired
    private QRUserService QRUserService;

    public userPrincipal(QRUser user) {
        this.user=user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return String.valueOf(user.getAccountNum());
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
        return QRUserService.isActive(user.getAccountNum());
    }
}
