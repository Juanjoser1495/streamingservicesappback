package com.selfdevelopment.streamingapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.selfdevelopment.streamingapp.entity.RankingTop10Mapped;
import com.selfdevelopment.streamingapp.entity.database.Movie;
import com.selfdevelopment.streamingapp.entity.database.Serie;
import com.selfdevelopment.streamingapp.entity.model.request.AddVoteRequest;
import com.selfdevelopment.streamingapp.mockobjects.GenreServiceObject;
import com.selfdevelopment.streamingapp.mockobjects.RankingServiceObjects;
import com.selfdevelopment.streamingapp.repository.GenreRepository;
import com.selfdevelopment.streamingapp.repository.MovieRepository;
import com.selfdevelopment.streamingapp.repository.SerieRepository;
import com.selfdevelopment.streamingapp.service.impl.RankingServiceImpl;

@ExtendWith(MockitoExtension.class)
public class RankingServiceTest {
	
	@Mock
	GenreRepository genreRepository;
	
	@Mock
	SerieRepository serieRepository;
	
	@Mock
	MovieRepository movieRepository;
	
	@InjectMocks
	RankingServiceImpl rankingServiceImpl;
	
	@Test
	public void testRankigService_FetchTop10ByGenre() {
		Mockito.when(genreRepository.findTop10ByGenre(anyString())).thenReturn(RankingServiceObjects.getRankingTop10ByGenreObject());
		
		List<RankingTop10Mapped> result = rankingServiceImpl.fetchTop10ByGenre("action");
		
		List<RankingTop10Mapped> listExpected = RankingServiceObjects.getRankingTop10MappedByGenreObject();
		
		assertEquals(listExpected.get(0).getGenre(), result.get(0).getGenre());
		assertEquals(listExpected.get(0).getMovieName(), result.get(0).getMovieName());
		assertEquals(listExpected.get(0).getImageUrl(), result.get(0).getImageUrl());
		
		assertEquals(listExpected.get(1).getGenre(), result.get(1).getGenre());
		assertEquals(listExpected.get(1).getMovieName(), result.get(1).getMovieName());
		assertEquals(listExpected.get(1).getImageUrl(), result.get(1).getImageUrl());
	}
	
	
	@Test
	public void testRankigService_FetchTop10AllGenre() {
		Mockito.when(genreRepository.findTop10AllGenre()).thenReturn(RankingServiceObjects.getRankingTop10AllGenreObject());
		
		List<RankingTop10Mapped> result = rankingServiceImpl.fetchTop10AllGenre();
		
		List<RankingTop10Mapped> listExpected = RankingServiceObjects.getRankingTop10MappedAllGenreObject();
		
		assertEquals(listExpected.get(0).getGenre(), result.get(0).getGenre());
		assertEquals(listExpected.get(0).getMovieName(), result.get(0).getMovieName());
		assertEquals(listExpected.get(0).getImageUrl(), result.get(0).getImageUrl());
		
		assertEquals(listExpected.get(1).getGenre(), result.get(1).getGenre());
		assertEquals(listExpected.get(1).getMovieName(), result.get(1).getMovieName());
		assertEquals(listExpected.get(1).getImageUrl(), result.get(1).getImageUrl());
	}
	
	
	@Test
	public void testRankigService_AddAVoteMovieSuccessful() {
		Mockito.when(movieRepository.findByMovieNameIgnoreCase(anyString())).thenReturn(RankingServiceObjects.getMovieObject());
		Mockito.when(movieRepository.save(any(Movie.class))).thenReturn(RankingServiceObjects.getMovieObject());
		
		boolean result = rankingServiceImpl.addAVote(new AddVoteRequest("fast and furious"));
				
		assertEquals(result, true);
	}
	
	@Test
	public void testRankigService_AddAVoteMovieError() {
		Mockito.when(movieRepository.findByMovieNameIgnoreCase(anyString())).thenReturn(RankingServiceObjects.getMovieObject());
		Mockito.when(movieRepository.save(any(Movie.class))).thenReturn(null);
		
		boolean result = rankingServiceImpl.addAVote(new AddVoteRequest("fast and furious"));
				
		assertEquals(result, false);
	}
	
	@Test
	public void testRankigService_AddAVoteSerieSuccessful() {
		Mockito.when(movieRepository.findByMovieNameIgnoreCase(anyString())).thenReturn(null);
		Mockito.when(serieRepository.findBySerieNameIgnoreCase(anyString())).thenReturn(RankingServiceObjects.getSerieObject());
		Mockito.when(serieRepository.save(any(Serie.class))).thenReturn(RankingServiceObjects.getSerieObject());
		
		boolean result = rankingServiceImpl.addAVote(new AddVoteRequest("the punisher"));
				
		assertEquals(result, true);
	}
	
	@Test
	public void testRankigService_AddAVoteSerieError() {
		Mockito.when(movieRepository.findByMovieNameIgnoreCase(anyString())).thenReturn(null);
		Mockito.when(serieRepository.findBySerieNameIgnoreCase(anyString())).thenReturn(RankingServiceObjects.getSerieObject());
		Mockito.when(serieRepository.save(any(Serie.class))).thenReturn(null);
		
		
		boolean result = rankingServiceImpl.addAVote(new AddVoteRequest("the punisher"));
				
		assertEquals(result, false);
	}
	
	@Test
	public void testRankigService_RankingGenres() {
		Map<String, Integer> expect = RankingServiceObjects.getRankingGenres();
		
		Mockito.when(genreRepository.findAll()).thenReturn(GenreServiceObject.getListGenre());
		Mockito.when(movieRepository.findAll()).thenReturn(RankingServiceObjects.getListMovieObject());
		Mockito.when(serieRepository.findAll()).thenReturn(RankingServiceObjects.getListSerieObject());
		
		Map<String,Integer> result = rankingServiceImpl.rankingGenres();
				
		assertEquals(result, expect);
	}


	

}
