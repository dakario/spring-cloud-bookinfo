package com.bookinfo.client;

import org.springframework.beans.factory.annotation.Autowired; 

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ReviewsService {

	@Autowired
	RestTemplate restTemplate;
	
	@SuppressWarnings("unchecked")
	public List<Object> getReviewsByIdProduct(@PathVariable int idProduct){
    	return restTemplate.getForObject("http://reviews/reviews/"+idProduct, List.class);
    }
}
