package com.selfdevelopment.streamingapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.selfdevelopment.streamingapp.entity.RankingTop10Mapped;
import com.selfdevelopment.streamingapp.entity.model.response.RankingTop10Response;
import com.selfdevelopment.streamingapp.service.RankingService;
import com.selfdevelopment.streamingapp.utils.StreamingAppConstants;

@RestController
public class StreamingAppController {

	private final RankingService rankingService;
	
	@Autowired
	public StreamingAppController(RankingService rankingService) {
		this.rankingService = rankingService;
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/genre/{category}")
	public ResponseEntity<RankingTop10Response> getTop10FromGenre(@PathVariable("category") String category){
		List<RankingTop10Mapped> rankingTop10 = rankingService.fetchTop10ByGenre(category);
		if(!rankingTop10.isEmpty()) {
			return new ResponseEntity<>(new RankingTop10Response(StreamingAppConstants.DATA_SUCCESSUFULL_RETRIEVED,rankingTop10), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(new RankingTop10Response(StreamingAppConstants.CATEGORY_NOT_FOUND_OR_NO_RECORDS_ON_THAT_CATEGORY,null), HttpStatus.OK);
		}
	}
}
