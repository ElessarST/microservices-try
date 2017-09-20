package com.example.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

import javax.validation.Valid
import java.security.Principal
/**
 * @author Aydar Farrakhov 
 * @since 20.09.2017
 */
@RestController
@RequestMapping("/users")
class UserController {

    @Autowired
    UserService userService

    @RequestMapping(value = "/current", method = RequestMethod.GET)
    Principal getUser(Principal principal) {
        return principal
    }

    @RequestMapping(method = RequestMethod.POST)
    void createUser(@Valid @RequestBody User user) {
        userService.create(user)
    }
}
