package com.bookinfo.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

@Service
public class RatingsService {
	@Autowired
	RestTemplate restTemplate;
	
    public Object getRatingByIdReviews(@PathVariable int idReviews) {
    	return restTemplate.getForObject("http://ratings/ratings/"+idReviews, Object.class);
    }
}
