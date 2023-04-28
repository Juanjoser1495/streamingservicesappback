package com.selfdevelopment.streamingapp.controller;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.selfdevelopment.streamingapp.entity.model.request.AddRemoveVoteRequest;
import com.selfdevelopment.streamingapp.mockobjects.RankingServiceObjects;
import com.selfdevelopment.streamingapp.service.RankingService;

@WebMvcTest(controllers = RankingController.class)
public class RankingControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	RankingService rankingService;

	@Test
	public void testRankingController_GetTop10FromGenreSuccessful() throws Exception {
		Mockito.when(rankingService.fetchTop10ByGenre(anyString()))
				.thenReturn(RankingServiceObjects.getRankingTop10MappedByGenreObject());

		mockMvc.perform(get("/ranking/action")).andExpect(status().isOk())
				.andExpect(jsonPath("$.message", is("Data successufull retrieved")))
				.andExpect(jsonPath("$.data.size()", is(2)));
	}

	@Test
	public void testRankingController_GetTop10FromGenreError() throws Exception {
		Mockito.when(rankingService.fetchTop10ByGenre(anyString())).thenReturn(new ArrayList<>());

		mockMvc.perform(get("/ranking/action")).andExpect(status().isOk())
				.andExpect(jsonPath("$.message", is("Category not found or no records on that category")))
				.andExpect(jsonPath("$.data.size()", anyOf(nullValue())));
	}

	@Test
	public void testRankingController_GetTop10AllGenreSuccessful() throws Exception {
		Mockito.when(rankingService.fetchTop10AllGenre())
				.thenReturn(RankingServiceObjects.getRankingTop10MappedAllGenreObject());

		mockMvc.perform(get("/ranking")).andExpect(status().isOk())
				.andExpect(jsonPath("$.message", is("Data successufull retrieved")))
				.andExpect(jsonPath("$.data.size()", is(2)));
	}

	@Test
	public void testRankingController_GetTop10AllGenreError() throws Exception {
		Mockito.when(rankingService.fetchTop10AllGenre()).thenReturn(new ArrayList<>());

		mockMvc.perform(get("/ranking")).andExpect(status().isOk())
				.andExpect(jsonPath("$.message", is("Category not found or no records on that category")))
				.andExpect(jsonPath("$.data.size()", anyOf(nullValue())));
	}

	@Test
	public void testRankingController_AddAVoteSuccessful() throws Exception {
		Mockito.when(rankingService.addAVote(any(AddRemoveVoteRequest.class))).thenReturn(true);

		mockMvc.perform(post("/like").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(new ObjectMapper().writeValueAsString("{\"title\":\"Crepusculo\"}")))
				.andExpect(status().isOk()).andExpect(jsonPath("$.message", is("Data updated")));
	}

	@Test
	public void testRankingController_AddAVoteError() throws Exception {
		Mockito.when(rankingService.addAVote(any(AddRemoveVoteRequest.class))).thenReturn(false);

		mockMvc.perform(post("/like").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(new ObjectMapper().writeValueAsString("{\"title\":\"Crepusculo\"}")))
				.andExpect(status().isInternalServerError())
				.andExpect(jsonPath("$.message", is("Internal Server Error")));
	}

	@Test
	public void testRankingController_RankingGenresSuccessful() throws Exception {
		Mockito.when(rankingService.rankingGenres()).thenReturn(RankingServiceObjects.getRankingGenres());

		mockMvc.perform(get("/ranking/genres")).andExpect(status().isOk())
				.andExpect(jsonPath("$.message", is("Data successufull retrieved")))
				.andExpect(jsonPath("$.data.size()", is(2)));
	}

	@Test
	public void testRankingController_RankingGenresError() throws Exception {
		Mockito.when(rankingService.rankingGenres()).thenReturn(new HashMap<>());

		mockMvc.perform(get("/ranking/genres")).andExpect(status().isInternalServerError())
				.andExpect(jsonPath("$.message", is("Internal Server Error")));
	}

}
