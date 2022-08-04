package com.selfdevelopment.streamingapp.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.selfdevelopment.streamingapp.service.RankingService;

@RestController
public class StreamingAppController {

	private final RankingService rankingService;
	
	@Autowired
	public StreamingAppController(RankingService rankingService) {
		this.rankingService = rankingService;
	}
	
	@GetMapping("/genre/{category}")
	public ResponseEntity<String> getTop10FromGenre(@PathParam("category") String category){
		return null;
	}
}
