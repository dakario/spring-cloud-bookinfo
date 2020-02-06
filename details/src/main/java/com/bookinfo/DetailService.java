package com.bookinfo;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

@Service
public class DetailService {

	private static List<Detail> details = Arrays.asList(
			new Detail(1, "William Shakespeare", 1595, "paperback", 200, "PublisherA", "English", "1234567890", "123-1234567890"),
			new Detail(2, "Rachel Aaron", 1595, "paperback", 100, "PublisherB", "English", "3434547089", "343-3434547089"),
			new Detail(3, "Abd al-Qadir al-Fasi", 1595, "paperback", 150, "PublisherC", "English", "5437897234", "543-5437897234"),
			new Detail(4, "Shana Abé", 1595, "paperback", 300, "PublisherD", "English", "3234143569", "323-3234143569")
			); 
	
	public Optional<Detail> getDetailById(int id) {
		return details.stream()
				.filter(detail -> detail.getId() == id)
				.findFirst();
	}
		
}
