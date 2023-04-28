package com.selfdevelopment.streamingapp.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfdevelopment.streamingapp.entity.RankingTop10Mapped;
import com.selfdevelopment.streamingapp.entity.customqueries.RankingTop10;
import com.selfdevelopment.streamingapp.entity.database.Genre;
import com.selfdevelopment.streamingapp.entity.database.Movie;
import com.selfdevelopment.streamingapp.entity.database.Serie;
import com.selfdevelopment.streamingapp.entity.model.request.AddRemoveVoteRequest;
import com.selfdevelopment.streamingapp.repository.GenreRepository;
import com.selfdevelopment.streamingapp.repository.MovieRepository;
import com.selfdevelopment.streamingapp.repository.SerieRepository;
import com.selfdevelopment.streamingapp.service.RankingService;

@Service
public class RankingServiceImpl implements RankingService {

	private final GenreRepository genreRepository;
	private final MovieRepository movieRepository;
	private final SerieRepository serieRepository;

	@Autowired
	public RankingServiceImpl(GenreRepository genreRepository, MovieRepository movieRepository,
			SerieRepository serieRepository) {
		this.genreRepository = genreRepository;
		this.serieRepository = serieRepository;
		this.movieRepository = movieRepository;
	}

	@Override
	public List<RankingTop10Mapped> fetchTop10ByGenre(String category) {

		List<RankingTop10> listTop10ByGenre = genreRepository.findTop10ByGenre(category);

		List<RankingTop10Mapped> response = new ArrayList<>();
		listTop10ByGenre.forEach((element) -> {
			RankingTop10Mapped rankingElement = new RankingTop10Mapped();
			rankingElement.setId(new Random().nextLong());
			rankingElement.setGenre(element.getGenre());
			rankingElement.setImageUrl(element.getImageurl());
			rankingElement.setMovieName(element.getMoviename());
			rankingElement.setLikes(element.getLikes());
			rankingElement.setPosition(element.getPosition());
			rankingElement.setDescription(element.getDescription());

			response.add(rankingElement);
		});

		return response;
	}

	@Override
	public List<RankingTop10Mapped> fetchTop10AllGenre() {
		List<RankingTop10> listTop10 = genreRepository.findTop10AllGenre();
		List<RankingTop10Mapped> response = new ArrayList<>();

		listTop10.forEach((element) -> {
			RankingTop10Mapped rankingElement = new RankingTop10Mapped();
			rankingElement.setId(new Random().nextLong());
			rankingElement.setGenre(element.getGenre());
			rankingElement.setImageUrl(element.getImageurl());
			rankingElement.setMovieName(element.getMoviename());
			rankingElement.setLikes(element.getLikes());
			rankingElement.setPosition(element.getPosition());
			rankingElement.setDescription(element.getDescription());

			response.add(rankingElement);
		});

		return response;
	}

	@Override
	public boolean addAVote(AddRemoveVoteRequest addRemoveVoteRequest) {
		boolean isUpdated = false;

		Movie movie = movieRepository.findByMovieNameIgnoreCase(addRemoveVoteRequest.getTitle());
		if (movie != null) {
			movie.setLikes(movie.getLikes() + 1);
			movie = movieRepository.save(movie);

			isUpdated = movie != null;
		} else {
			Serie serie = serieRepository.findBySerieNameIgnoreCase(addRemoveVoteRequest.getTitle());

			if (serie != null) {
				serie.setLikes(serie.getLikes() + 1);
				serie = serieRepository.save(serie);

				isUpdated = serie != null;
			}
		}

		return isUpdated;
	}

	@Override
	public boolean removeAVote(AddRemoveVoteRequest addRemoveVoteRequest) {
		boolean isUpdated = false;

		Movie movie = movieRepository.findByMovieNameIgnoreCase(addRemoveVoteRequest.getTitle());
		if (movie != null) {
			movie.setLikes(movie.getLikes() - 1);
			movie = movieRepository.save(movie);

			isUpdated = movie != null;
		} else {
			Serie serie = serieRepository.findBySerieNameIgnoreCase(addRemoveVoteRequest.getTitle());

			if (serie != null) {
				serie.setLikes(serie.getLikes() - 1);
				serie = serieRepository.save(serie);

				isUpdated = serie != null;
			}
		}

		return isUpdated;
	}
	
	@Override
	public Map<String, Integer> rankingGenres() {
		List<String> listGenre = genreRepository.findAll().stream().map(Genre::getGenre).collect(Collectors.toList());
		
		List<Movie> listMovies = movieRepository.findAll();
		List<Serie> listSeries = serieRepository.findAll();
		Map<String, Integer> listOfGenresByLikes =  new HashMap<>();
		
		listGenre.forEach(genre ->{
			int likesMovies = listMovies.stream().filter(movie -> movie.getGenre().getGenre().equals(genre)).mapToInt(Movie::getLikes).sum();
			int likesSeries = listSeries.stream().filter(serie -> serie.getGenre().getGenre().equals(genre)).mapToInt(Serie::getLikes).sum();
			
			listOfGenresByLikes.put(genre, likesMovies+likesSeries);
		});
		
		return listOfGenresByLikes;
	}

}
