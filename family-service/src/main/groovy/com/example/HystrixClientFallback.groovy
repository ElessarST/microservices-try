package com.example

import org.springframework.stereotype.Service

/**
 * @author Aydar Farrakhov 
 * @since 05.04.2017
 */
@Service
class HystrixClientFallback implements NewsService{


    @Override
    String iFailSometimes() {
        return "fail"
    }
}
