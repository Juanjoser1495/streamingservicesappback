package com.selfdevelopment.streamingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.selfdevelopment.streamingapp.entity.Serie;

public interface SerieRepository extends JpaRepository<Serie, Long> {

}
