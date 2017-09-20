package com.example

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

import java.security.Principal

/**
 * @author Aydar Farrakhov 
 * @since 20.09.2017
 */
@RestController
class NewsController {

    @PreAuthorize("#oauth2.hasScope('server')")
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    def get(@PathVariable String name, Principal principal) {
        return "hello from news service ${name} ${principal.name}"
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    def get(Principal principal) {
        return "hello from news service ${principal.name}"
    }

}
