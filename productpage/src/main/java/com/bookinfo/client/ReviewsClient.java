package com.bookinfo.client;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "reviews")
@RibbonClient(name = "reviews")
public interface ReviewsClient {
    @RequestMapping(method = RequestMethod.GET, value = "/reviews/{idProduct}")
    List<Object> getReviews(@PathVariable int idProduct);
}
