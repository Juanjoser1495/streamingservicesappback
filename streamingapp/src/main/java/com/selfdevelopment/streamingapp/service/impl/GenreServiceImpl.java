package com.selfdevelopment.streamingapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfdevelopment.streamingapp.entity.database.Genre;
import com.selfdevelopment.streamingapp.repository.GenreRepository;
import com.selfdevelopment.streamingapp.service.GenreService;

@Service
public class GenreServiceImpl implements GenreService {

	private final GenreRepository genreRepository;

	@Autowired
	public GenreServiceImpl(GenreRepository genreRepository) {
		this.genreRepository = genreRepository;
	}

	@Override
	public List<Genre> findAllGenres() {
		return genreRepository.findAll();
	}

}
