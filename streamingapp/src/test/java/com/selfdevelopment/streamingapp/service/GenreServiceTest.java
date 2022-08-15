package com.selfdevelopment.streamingapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.selfdevelopment.streamingapp.entity.database.Genre;
import com.selfdevelopment.streamingapp.mockobjects.GenreServiceObject;
import com.selfdevelopment.streamingapp.repository.GenreRepository;
import com.selfdevelopment.streamingapp.service.impl.GenreServiceImpl;

@ExtendWith(MockitoExtension.class)
public class GenreServiceTest {
	
	@Mock
	GenreRepository genreRepository;
	
	@InjectMocks
	GenreServiceImpl genreServiceImpl;

	@Test
	public void testGenreService_FindAllUser() {
			
		Mockito.when(genreRepository.findAll()).thenReturn(GenreServiceObject.getListGenre());
		
		List<Genre> listGenreExecuted = genreServiceImpl.findAllGenres();
		
		assertEquals(GenreServiceObject.getListGenre(), listGenreExecuted);
	}

}
