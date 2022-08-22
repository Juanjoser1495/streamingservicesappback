package com.selfdevelopment.streamingapp.entity.model.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenericListObjectResponse {

	private String message;
	private List<?> data;
}
