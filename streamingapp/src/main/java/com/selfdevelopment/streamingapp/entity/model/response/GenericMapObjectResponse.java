package com.selfdevelopment.streamingapp.entity.model.response;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenericMapObjectResponse {

	private String message;
	private Map<?,?> data;
}
