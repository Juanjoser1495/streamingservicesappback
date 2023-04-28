package com.selfdevelopment.streamingapp.service;

import java.util.List;
import java.util.Map;

import com.selfdevelopment.streamingapp.entity.RankingTop10Mapped;
import com.selfdevelopment.streamingapp.entity.model.request.AddRemoveVoteRequest;

public interface RankingService {
	
	List<RankingTop10Mapped> fetchTop10ByGenre(String category);
	List<RankingTop10Mapped> fetchTop10AllGenre();
	boolean addAVote(AddRemoveVoteRequest title);
	boolean removeAVote(AddRemoveVoteRequest title);
	Map<String, Integer> rankingGenres();

}
