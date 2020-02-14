package com.bookinfo.client;

import com.bookinfo.entity.Details;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "details")
@RibbonClient(name = "details")
public interface DetailsClient {
    @RequestMapping(method = RequestMethod.GET, value = "/details/{idProduct}")
    Details getDetail(@PathVariable int idProduct);
}
