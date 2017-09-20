package com.example.user

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.util.Assert

/**
 * @author Aydar Farrakhov 
 * @since 20.09.2017
 */
@Service
class UserServiceImpl implements UserService {
    
    private final Logger log = LoggerFactory.getLogger(getClass())

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder()
    
    @Autowired
    UserRepository repository
    
    @Override
    void create(User user) {

        User existing = repository.findByEmail(user.getEmail())
        Assert.isNull(existing, "user already exists: ${user.email}")

        String hash = encoder.encode(user.getPassword())
        user.setPassword(hash)

        repository.save(user)

        log.info("new user has been created: ${user.email}")
    }
}
