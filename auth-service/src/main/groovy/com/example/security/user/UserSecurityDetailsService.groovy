package com.example.security.user

import com.example.user.User
import com.example.user.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
/**
 * @author Aydar Farrakhov 
 * @since 19.09.2017
 */
@Service
class UserSecurityDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
        if (user == null) throw new UsernameNotFoundException("User not found")
        return new UserSecure(user)
    }

}
