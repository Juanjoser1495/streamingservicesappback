package com.selfdevelopment.streamingapp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.selfdevelopment.streamingapp.entity.RankingTop10Mapped;
import com.selfdevelopment.streamingapp.entity.model.request.AddVoteRequest;
import com.selfdevelopment.streamingapp.entity.model.response.GenericMapObjectResponse;
import com.selfdevelopment.streamingapp.entity.model.response.RankingTop10Response;
import com.selfdevelopment.streamingapp.entity.model.response.StringResponse;
import com.selfdevelopment.streamingapp.service.RankingService;
import com.selfdevelopment.streamingapp.utils.StreamingAppConstants;

@RestController
public class RankingController {

	private final RankingService rankingService;

	@Autowired
	public RankingController(RankingService rankingService) {
		this.rankingService = rankingService;
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/ranking/{genre}")
	public ResponseEntity<RankingTop10Response> getTop10FromGenre(@PathVariable("genre") String category) {
		List<RankingTop10Mapped> rankingTop10ByGenre = rankingService.fetchTop10ByGenre(category);
		if (!rankingTop10ByGenre.isEmpty()) {
			return new ResponseEntity<>(
					new RankingTop10Response(StreamingAppConstants.DATA_SUCCESSFULL_RETRIEVED, rankingTop10ByGenre),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new RankingTop10Response(
					StreamingAppConstants.CATEGORY_NOT_FOUND_OR_NO_RECORDS_ON_THAT_CATEGORY, null), HttpStatus.OK);
		}
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/ranking")
	public ResponseEntity<RankingTop10Response> getTop10AllGenre() {
		List<RankingTop10Mapped> rankingTop10 = rankingService.fetchTop10AllGenre();
		if (!rankingTop10.isEmpty()) {
			return new ResponseEntity<>(
					new RankingTop10Response(StreamingAppConstants.DATA_SUCCESSFULL_RETRIEVED, rankingTop10),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new RankingTop10Response(
					StreamingAppConstants.CATEGORY_NOT_FOUND_OR_NO_RECORDS_ON_THAT_CATEGORY, null), HttpStatus.OK);
		}
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping(value = "/like")
	public ResponseEntity<StringResponse> addAVote(@RequestBody AddVoteRequest addVoteRequest) {
		boolean isUpdated = rankingService.addAVote(addVoteRequest);
		if (isUpdated) {
			return new ResponseEntity<>(new StringResponse(StreamingAppConstants.SUCCESSFULL_UPDATED), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StringResponse(StreamingAppConstants.INTERNAL_SERVER_ERROR),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping(value = "/ranking/genres")
	public ResponseEntity<GenericMapObjectResponse> rankingGenres() {
		Map<String, Integer> rankingGenres = rankingService.rankingGenres();
		if (!rankingGenres.isEmpty()) {
			return new ResponseEntity<>(
					new GenericMapObjectResponse(StreamingAppConstants.DATA_SUCCESSFULL_RETRIEVED, rankingGenres),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new GenericMapObjectResponse(StreamingAppConstants.INTERNAL_SERVER_ERROR, null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
