package com.dianabol.Backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Expansion {

	@Id
	private int idExpansion;
	
	@Column
	private String enName;
	
	@Column
	private String releaseDate;
	
	@Column
	private int idGame;

	public int getIdExpansion() {
		return idExpansion;
	}

	public void setIdExpansion(int idExpansion) {
		this.idExpansion = idExpansion;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public int getIdGame() {
		return idGame;
	}

	public void setIdGame(int idGame) {
		this.idGame = idGame;
	}
	
	
}
