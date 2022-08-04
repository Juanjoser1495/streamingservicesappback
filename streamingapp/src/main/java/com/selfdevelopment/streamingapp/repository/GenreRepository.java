package com.selfdevelopment.streamingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.selfdevelopment.streamingapp.entity.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long>{

}
