package com.selfdevelopment.streamingapp.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfdevelopment.streamingapp.entity.RankingTop10Mapped;
import com.selfdevelopment.streamingapp.entity.customqueries.RankingTop10;
import com.selfdevelopment.streamingapp.repository.GenreRepository;
import com.selfdevelopment.streamingapp.service.RankingService;

@Service
public class RankingServiceImpl implements RankingService {

	private final GenreRepository genreRepository;

	@Autowired
	public RankingServiceImpl(GenreRepository genreRepository) {
		this.genreRepository = genreRepository;
	}

	@Override
	public List<RankingTop10Mapped> fetchTop10ByGenre(String category) {
		
		List<RankingTop10> listTop10 = genreRepository.findTop10ByGenre(category);
		
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

}
