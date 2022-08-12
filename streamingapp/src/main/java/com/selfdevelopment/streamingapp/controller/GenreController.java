package com.selfdevelopment.streamingapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.selfdevelopment.streamingapp.entity.database.Genre;
import com.selfdevelopment.streamingapp.entity.model.response.GenericObjectResponse;
import com.selfdevelopment.streamingapp.service.GenreService;
import com.selfdevelopment.streamingapp.utils.StreamingAppConstants;

@RestController
public class GenreController {

	private final GenreService genreService;

	@Autowired
	public GenreController(GenreService genreService) {
		this.genreService = genreService;
	}
	
	@GetMapping("/genres")
	public ResponseEntity<GenericObjectResponse> findAllGenre(){
		List<Genre> listGenre = genreService.findAllGenres();
		if(!listGenre.isEmpty()) {
			return new ResponseEntity<>(new GenericObjectResponse(StreamingAppConstants.DATA_SUCCESSFULL_RETRIEVED,listGenre), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(new GenericObjectResponse(StreamingAppConstants.CATEGORY_NOT_FOUND_OR_NO_RECORDS_ON_THAT_CATEGORY,null), HttpStatus.OK);
		}
	}
}
