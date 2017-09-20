package com.example.user

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

/**
 * @author Aydar Farrakhov 
 * @since 19.09.2017
 */
@Service
class DumUserDetailsService implements UserDetailsService {

    private Map<String, String> users = [user1: "password1", user2: "password2"]

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        def password = users.get(username)
        if (password == null) throw new UsernameNotFoundException("User not found")
        return new User(username, password)
    }
    
    static class User implements UserDetails {
        private String username

        private String password

        User(String username, String password) {
            this.username = username
            this.password = password
        }

        @Override
        String getPassword() {
            return password
        }

        @Override
        String getUsername() {
            return username
        }

        @Override
        List<GrantedAuthority> getAuthorities() {
            return Arrays.asList(new SimpleGrantedAuthority("USER"))
        }

        @Override
        boolean isAccountNonExpired() {
            return true
        }

        @Override
        boolean isAccountNonLocked() {
            return true
        }

        @Override
        boolean isCredentialsNonExpired() {
            return true
        }

        @Override
        boolean isEnabled() {
            return true
        }
    }

}
