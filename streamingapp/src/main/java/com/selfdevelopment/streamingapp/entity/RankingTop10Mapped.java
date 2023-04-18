package com.selfdevelopment.streamingapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RankingTop10Mapped {

	private Long id;
	private String movieName;
	private String imageUrl;
	private String genre;
	private Integer likes;
	private Integer position;
}
