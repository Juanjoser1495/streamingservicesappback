package com.selfdevelopment.streamingapp.entity.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="movie")
@Table(name="movie")
@Data
@NoArgsConstructor
public class Movie {
	
	@Id
	@GeneratedValue
	@Column(name="idmovie")
	private Long idMovie;
	
	@Column(name="moviename")
	private String movieName;
	
	@Column(name="imageurl")
	private String imageUrl;
	
	@OneToOne
	@JoinColumn(name="genreid")
	private Genre genre;
}
