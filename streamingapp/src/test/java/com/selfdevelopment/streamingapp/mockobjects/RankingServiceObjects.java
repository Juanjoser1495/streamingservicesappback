package com.selfdevelopment.streamingapp.mockobjects;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;

import com.selfdevelopment.streamingapp.entity.RankingTop10Mapped;
import com.selfdevelopment.streamingapp.entity.customqueries.RankingTop10;
import com.selfdevelopment.streamingapp.entity.database.Genre;
import com.selfdevelopment.streamingapp.entity.database.Movie;
import com.selfdevelopment.streamingapp.entity.database.Serie;

public class RankingServiceObjects {

	static ProjectionFactory factory = new SpelAwareProxyProjectionFactory();
	public static List<RankingTop10> getRankingTop10ByGenreObject() {
		List<RankingTop10> listRankingTop10Mock = new ArrayList<>();

		
		RankingTop10 theGreyMan = factory.createProjection(RankingTop10.class);
		theGreyMan.setGenre("action");
		theGreyMan.setImageurl("http://somePath.com/thegreyman");
		theGreyMan.setMoviename("The Grey Man");
		theGreyMan.setLikes(10);

		listRankingTop10Mock.add(theGreyMan);

		RankingTop10 jumanji = factory.createProjection(RankingTop10.class);
		jumanji.setGenre("action");
		jumanji.setImageurl("http://somePath.com/jumanji");
		jumanji.setMoviename("Jumanji");
		jumanji.setLikes(20);

		listRankingTop10Mock.add(jumanji);

		return listRankingTop10Mock;
	}
	
	
	public static List<RankingTop10> getRankingTop10AllGenreObject() {
		List<RankingTop10> listRankingTop10Mock = new ArrayList<>();

		
		RankingTop10 theGreyMan = factory.createProjection(RankingTop10.class);
		theGreyMan.setGenre("action");
		theGreyMan.setImageurl("http://somePath.com/thegreyman");
		theGreyMan.setMoviename("The Grey Man");
		theGreyMan.setLikes(10);

		listRankingTop10Mock.add(theGreyMan);

		RankingTop10 wandavision = factory.createProjection(RankingTop10.class);
		wandavision.setGenre("superhero");
		wandavision.setImageurl("http://somePath.com/wandavision");
		wandavision.setMoviename("Wandavision");
		wandavision.setLikes(20);

		listRankingTop10Mock.add(wandavision);

		return listRankingTop10Mock;
	}

	public static List<RankingTop10Mapped> getRankingTop10MappedByGenreObject() {
		List<RankingTop10Mapped> listRankingTop10Mapped = new ArrayList<>();

		RankingTop10Mapped theGreyMan = new RankingTop10Mapped(Long.valueOf(1), "The Grey Man",
				"http://somePath.com/thegreyman", "action");

		RankingTop10Mapped jumanji = new RankingTop10Mapped();
		jumanji.setGenre("action");
		jumanji.setImageUrl("http://somePath.com/jumanji");
		jumanji.setMovieName("Jumanji");
		jumanji.setId(Long.valueOf(2));

		listRankingTop10Mapped.add(theGreyMan);
		listRankingTop10Mapped.add(jumanji);

		return listRankingTop10Mapped;
	}
	
	public static List<RankingTop10Mapped> getRankingTop10MappedAllGenreObject() {
		List<RankingTop10Mapped> listRankingTop10Mapped = new ArrayList<>();

		RankingTop10Mapped theGreyMan = new RankingTop10Mapped(Long.valueOf(1), "The Grey Man",
				"http://somePath.com/thegreyman", "action");

		RankingTop10Mapped jumanji = new RankingTop10Mapped();
		jumanji.setGenre("superhero");
		jumanji.setImageUrl("http://somePath.com/wandavision");
		jumanji.setMovieName("Wandavision");
		jumanji.setId(Long.valueOf(2));

		listRankingTop10Mapped.add(theGreyMan);
		listRankingTop10Mapped.add(jumanji);

		return listRankingTop10Mapped;
	}
	
	public static Movie getMovieObject() {
		Movie fastAndFurios = new Movie();
		fastAndFurios.setGenre(new Genre(Long.valueOf(1), "action"));
		fastAndFurios.setIdMovie(Long.valueOf(1));
		fastAndFurios.setImageUrl("http://somePath.com/fastandfurious");
		fastAndFurios.setLikes(10);
		fastAndFurios.setMovieName("Fast and furious");
		
		return fastAndFurios;
	}
	
	public static Serie getSerieObject() {
		Serie thePunisher = new Serie();
		thePunisher.setGenre(new Genre(Long.valueOf(1), "action"));
		thePunisher.setIdSerie(Long.valueOf(1));
		thePunisher.setImageUrl("http://somePath.com/thepunisher");
		thePunisher.setLikes(14);
		thePunisher.setSerieName("The punisher");
		
		return thePunisher;
	}
	
}
