package com.selfdevelopment.streamingapp.entity.model.response;

import java.util.List;

import com.selfdevelopment.streamingapp.entity.RankingTop10Mapped;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RankingTop10Response {

	private String message;
	private List<RankingTop10Mapped> data;
}
