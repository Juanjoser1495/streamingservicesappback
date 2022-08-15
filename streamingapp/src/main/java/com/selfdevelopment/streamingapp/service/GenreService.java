package com.selfdevelopment.streamingapp.service;

import java.util.List;

import com.selfdevelopment.streamingapp.entity.database.Genre;

public interface GenreService {

	List<Genre> findAllGenres(); 	
	
}
