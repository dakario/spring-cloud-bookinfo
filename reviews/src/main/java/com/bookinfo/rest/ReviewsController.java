package com.bookinfo.rest;

import com.bookinfo.client.RatingsClient;
import com.bookinfo.entity.Reviews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ReviewsController {

    @Value("${enable_ratings}")
    boolean enable_ratings;

    @Value("${star_color}")
    String star_color;

    @Autowired
    private RatingsClient ratingsClient;

    @GetMapping("/reviews/{idProduct}")
    public List<Reviews> getReviews(@PathVariable int idProduct){
        List<Reviews> reviews =  getReviewsByIdProduct(idProduct);
        if (enable_ratings){
            reviews.forEach(reviews1 -> {
                reviews1.setRating(getRatings(reviews1.getIdReviewer()));
            });
        }
        return reviews;
    }
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return new ResponseEntity<String>("{\"status\": \"Reviews is healthy\"}", HttpStatus.OK);
    }
    private Object getRatings(int idReviews) {
        try {
            return ratingsClient.getRating(idReviews);
        }
        catch (Exception ex){
            System.out.println(ex);
            HashMap<String, String> mes = new HashMap<>();
            mes.put("error", "Ratings service is currently unavailable");
            return mes;
        }
    }
    private List<Reviews> getReviews(){
        List<Reviews> list = new ArrayList<>();
        Reviews reviews1 = Reviews.builder()
                .idReviewer(1)
                .color(star_color)
                .idProduct(1)
                .reviewer("reviewer1")
                .text("An extremely entertaining play by Shakespeare. The slapstick humour is refreshing!")
                .build();
        list.add(reviews1);
        Reviews reviews2 = Reviews.builder()
                .idReviewer(2)
                .idProduct(1)
                .color(star_color)
                .reviewer("reviewer2")
                .text("Absolutely fun and entertaining. The play lacks thematic depth when compared to other plays by Shakespeare.")
                .build();
        list.add(reviews2);
        Reviews reviews3 = Reviews.builder()
                .idReviewer(3)
                .idProduct(2)
                .color(star_color)
                .reviewer("reviewer3")
                .text("Absolutely fun and entertaining. The play lacks thematic depth when compared to other plays by Shakespeare.")
                .build();
        list.add(reviews3);
        return list;
    }
    private List<Reviews> getReviewsByIdProduct(int idProduct){
        return getReviews().stream()
                .filter(reviews -> reviews.getIdProduct() == idProduct)
                .collect(Collectors.toList());
    }
}
