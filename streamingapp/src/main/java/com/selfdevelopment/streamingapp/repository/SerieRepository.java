package com.selfdevelopment.streamingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.selfdevelopment.streamingapp.entity.database.Serie;

public interface SerieRepository extends JpaRepository<Serie, Long> {
	
	Serie findBySerieNameIgnoreCase(String serieName);

}
