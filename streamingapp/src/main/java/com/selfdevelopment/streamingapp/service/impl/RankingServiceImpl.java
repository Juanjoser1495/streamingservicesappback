package com.selfdevelopment.streamingapp.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfdevelopment.streamingapp.entity.RankingTop10Mapped;
import com.selfdevelopment.streamingapp.entity.customqueries.RankingTop10;
import com.selfdevelopment.streamingapp.entity.database.Movie;
import com.selfdevelopment.streamingapp.entity.database.Serie;
import com.selfdevelopment.streamingapp.entity.model.request.AddVoteRequest;
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
		listTop10ByGenre.stream().forEach((element) -> {
			RankingTop10Mapped rankingElement = new RankingTop10Mapped();
			rankingElement.setId(new Random().nextLong());
			rankingElement.setGenre(element.getGenre());
			rankingElement.setImageUrl(element.getImageurl());
			rankingElement.setMovieName(element.getMoviename());

			response.add(rankingElement);
		});

		return response;
	}

	@Override
	public List<RankingTop10Mapped> fetchTop10AllGenre() {
		List<RankingTop10> listTop10 = genreRepository.findTop10AllGenre();
		List<RankingTop10Mapped> response = new ArrayList<>();

		listTop10.stream().forEach((element) -> {
			RankingTop10Mapped rankingElement = new RankingTop10Mapped();
			rankingElement.setId(new Random().nextLong());
			rankingElement.setGenre(element.getGenre());
			rankingElement.setImageUrl(element.getImageurl());
			rankingElement.setMovieName(element.getMoviename());

			response.add(rankingElement);
		});

		return response;
	}

	@Override
	public boolean addAVote(AddVoteRequest addVoteRequest) {
		boolean isUpdated = false;

		Movie movie = movieRepository.findByMovieNameIgnoreCase(addVoteRequest.getTitle());
		if (movie != null) {
			movie.setLikes(movie.getLikes() + 1);
			movie = movieRepository.save(movie);

			isUpdated = movie != null ? true : false;
		} else {
			Serie serie = serieRepository.findBySerieNameIgnoreCase(addVoteRequest.getTitle());

			if (serie != null) {
				serie.setLikes(serie.getLikes() + 1);
				serie = serieRepository.save(serie);

				isUpdated = serie != null ? true : false;
			}
		}

		return isUpdated;
	}

}
