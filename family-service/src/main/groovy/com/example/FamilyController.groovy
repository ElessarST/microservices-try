package com.example

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

import java.security.Principal

/**
 * @author Aydar Farrakhov 
 * @since 20.09.2017
 */
@RestController
class FamilyController {

    @Autowired
    NewsService newsService

    @RequestMapping(value = "/current", method = RequestMethod.GET)
    def get(Principal principal) {
        return newsService.iFailSometimes(principal.name)
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    def getFailed() {
        return newsService.iFailSometimes()
    }

}
