//package net.fitriandfriends.egringotts;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//import java.util.Collections;
//import java.util.List;
//
//public class CustomUserDetails implements UserDetails {
//
//    private Account account;
//
//    public CustomUserDetails(Account account) {
//
//        super();
//        this.account = account;
//
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//
//        return Collections.singleton(new SimpleGrantedAuthority(account.getUser().getType()));
//
//    }
//
//    @Override
//    public String getPassword() {
//        return account.getPassword();
//    }
//
//    @Override
//    public String getUsername() {
//        return account.getUsername();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
////        return UserDetails.super.isAccountNonExpired();
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
////        return UserDetails.super.isAccountNonLocked();
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
////        return UserDetails.super.isCredentialsNonExpired();
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
////        return UserDetails.super.isEnabled();
//    }
//
//}