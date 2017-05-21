package com.example;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Aydar Farrakhov
 * @since 05.04.2017
 */
@FeignClient(name = "news-service", fallback = HystrixClientFallback.class)
@Service
public interface NewsService {

    @RequestMapping(method = RequestMethod.GET, value = "/")
    String iFailSometimes();


}
