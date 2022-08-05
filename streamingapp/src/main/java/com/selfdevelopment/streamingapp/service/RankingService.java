package com.selfdevelopment.streamingapp.service;

import java.util.List;

import com.selfdevelopment.streamingapp.entity.RankingTop10Mapped;

public interface RankingService {
	
	List<RankingTop10Mapped> fetchTop10ByGenre(String category);

}
