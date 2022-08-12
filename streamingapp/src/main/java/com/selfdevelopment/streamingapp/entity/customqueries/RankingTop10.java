package com.selfdevelopment.streamingapp.entity.customqueries;

public interface RankingTop10 {

	String getMoviename();
	String getImageurl();
	String getGenre();
	Integer getLikes();
	
	String setMoviename(String movieName);
	String setImageurl(String imageUrl);
	String setGenre(String genre);
	Integer setLikes(Integer likes);
}
