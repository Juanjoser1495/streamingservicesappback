package com.selfdevelopment.streamingapp.service;

import java.util.List;

import com.selfdevelopment.streamingapp.entity.RankingTop10Mapped;
import com.selfdevelopment.streamingapp.entity.model.request.AddVoteRequest;

public interface RankingService {
	
	List<RankingTop10Mapped> fetchTop10ByGenre(String category);
	List<RankingTop10Mapped> fetchTop10AllGenre();
	boolean addAVote(AddVoteRequest title);

}
