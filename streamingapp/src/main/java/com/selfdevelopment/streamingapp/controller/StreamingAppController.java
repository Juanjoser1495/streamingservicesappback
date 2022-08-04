package com.selfdevelopment.streamingapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StreamingAppController {

	@GetMapping("/genre")
	public ResponseEntity<String> getTop10FromGenre(){
		return null;
	}
}
