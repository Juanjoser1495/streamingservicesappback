package com.selfdevelopment.streamingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.selfdevelopment.streamingapp.entity.database.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
	
	Movie findByMovieNameIgnoreCase(String movieName);

}
