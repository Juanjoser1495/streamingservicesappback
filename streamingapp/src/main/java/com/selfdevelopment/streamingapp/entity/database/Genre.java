package com.selfdevelopment.streamingapp.entity.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="genre")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Genre {

	@Id
	@GeneratedValue
	@Column(name="idgenre")
	private Long idGenre;
	
	@Column(name="genre")
	private String genre;
}
