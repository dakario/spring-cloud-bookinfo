package com.bookinfo;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class ReviewsService {
	
	private static final List<Review> reviews = Arrays.asList(
			new Review(1,1, "reviewer1", "text write by reviewer1"),
			new Review(2,1, "reviewer2", "text write by reviewer2"),
			new Review(3,2, "reviewer1", "text write by reviewer1"),
			new Review(4,2, "reviewer2", "text write by reviewer2")
			);

	public Optional<Review> getByReviewId(int id) {
		return reviews.stream()
				.filter(review -> review.getId() == id)
				.findFirst();
	}
	
	public List<Review> getByProductId(int productId) {
		return reviews.stream()
				.filter(review -> review.getProductId() == productId)
				.collect(Collectors.toList());
	}
	
}
