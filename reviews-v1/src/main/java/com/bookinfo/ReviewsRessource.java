package com.bookinfo;

import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ReviewsRessource {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	ReviewsService reviewsService;
	
	@RequestMapping("/health")
    public Response health() {
        return Response.ok().type(MediaType.APPLICATION_JSON).entity("{\"status\": \"Reviews is healthy\"}").build();
    }
	
	@RequestMapping("/reviews/{productId}")
    public Response bookReviewsById(@PathVariable("productId") int productId, @RequestHeader HttpHeaders requestHeaders) {
      
      List<Review> reviews = reviewsService.getByProductId(productId);
      
      return Response.ok().type(MediaType.APPLICATION_JSON).entity(reviews).build();
	}
	
}
