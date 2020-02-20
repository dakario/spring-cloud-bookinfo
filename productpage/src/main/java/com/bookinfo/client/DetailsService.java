package com.bookinfo.client;

import com.bookinfo.entity.Details;  

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

@Service
public class DetailsService {
	
	@Autowired
	RestTemplate restTemplate;
	
    public Details getDetailByIdProduct(@PathVariable int idProduct) {
    	return restTemplate.getForObject("http://details/details/"+idProduct, Details.class);
    }
    
}
