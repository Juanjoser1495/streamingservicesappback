package com.selfdevelopment.streamingapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="genre")
@Data
@NoArgsConstructor
public class Genre {

	@Id
	@GeneratedValue
	@Column(name="idgenre")
	private Long idGenre;
	
	@Column(name="genre")
	private String genre;
}
