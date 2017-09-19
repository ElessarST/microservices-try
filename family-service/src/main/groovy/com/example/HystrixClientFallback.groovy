package com.example

import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PathVariable

/**
 * @author Aydar Farrakhov 
 * @since 05.04.2017
 */
@Service
class HystrixClientFallback implements NewsService{


    @Override
    String iFailSometimes(@PathVariable String name) {
        return "fail"
    }

    @Override
    String iFailSometimes() {
        return "fail"
    }
}
