package com.bookinfo.client;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "ratings")
@RibbonClient(name = "ratings")
public interface RatingsClient {
    @RequestMapping(method = RequestMethod.GET, value = "/ratings/{idReviews}")
    Object getRating(@PathVariable int idReviews);
}
