package com.bookinfo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/details")
public class DetailsRessource {

	@Autowired
	DetailService service;
	
	@RequestMapping("/{id}")
	public Optional<Detail> getDetail(@PathVariable final int id) {
		//Thread.sleep(50000);
		return service.getDetailById(id);
	}
}
