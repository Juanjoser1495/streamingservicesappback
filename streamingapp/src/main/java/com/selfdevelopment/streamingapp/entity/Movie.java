package com.selfdevelopment.streamingapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
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
	
	@Column(name="genreid")
	@OneToOne
	private Genre genre;
}