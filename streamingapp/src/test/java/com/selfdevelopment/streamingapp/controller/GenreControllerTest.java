package com.selfdevelopment.streamingapp.controller;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.selfdevelopment.streamingapp.mockobjects.GenreServiceObject;
import com.selfdevelopment.streamingapp.service.GenreService;

@WebMvcTest(controllers = GenreController.class)
public class GenreControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	GenreService genreService;

	@Test
	public void testGenreController_FindAllGenreSuccessful() throws Exception {
		Mockito.when(genreService.findAllGenres()).thenReturn(GenreServiceObject.getListGenre());

		mockMvc.perform(get("/genres")).andExpect(status().isOk()).andExpect(jsonPath("$.data.size()", is(2)))
				.andExpect(jsonPath("$.message", is("Data successufull retrieved")));

	}
	
	@Test
	public void testGenreController_FindAllGenreError() throws Exception {
		Mockito.when(genreService.findAllGenres()).thenReturn(new ArrayList<>());

		mockMvc.perform(get("/genres")).andExpect(status().isOk()).andExpect(jsonPath("$.data", anyOf(nullValue())))
				.andExpect(jsonPath("$.message", is("Genres not found")));

	}

}
