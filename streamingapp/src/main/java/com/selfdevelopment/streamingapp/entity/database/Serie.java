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

@Entity
@Table(name="serie")
@Data
@NoArgsConstructor
public class Serie {

	@Id
	@GeneratedValue
	@Column(name="idserie")
	private Long idSerie;
	
	@Column(name="nameserie")
	private String serieName;
	
	@Column(name="imageurl")
	private String imageUrl;
	
	@OneToOne
	@JoinColumn(name="genreid")
	private Genre genre;
}
