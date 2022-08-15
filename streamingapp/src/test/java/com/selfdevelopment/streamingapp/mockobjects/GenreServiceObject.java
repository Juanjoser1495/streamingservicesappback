package com.selfdevelopment.streamingapp.mockobjects;

import java.util.ArrayList;
import java.util.List;

import com.selfdevelopment.streamingapp.entity.database.Genre;

public class GenreServiceObject {

	public static List<Genre> getListGenre() {
		List<Genre> listGenre = new ArrayList<>();
		Genre actionGenre = new Genre(Long.valueOf(1), "action");
		Genre terrorGenre = new Genre(Long.valueOf(2), "terror");
		listGenre.add(actionGenre);
		listGenre.add(terrorGenre);
		return listGenre;
	}

}
