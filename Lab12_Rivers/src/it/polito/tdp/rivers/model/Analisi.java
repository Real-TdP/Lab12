package it.polito.tdp.rivers.model;

import java.time.LocalDate;

public class Analisi {
	private LocalDate dataS;
	private LocalDate dataE;
	private int nMisure;
	private float fMedia;
	
	public Analisi(LocalDate dataS, LocalDate dataE, int nMisure, float fMedia) {
		this.dataS = dataS;
		this.dataE = dataE;
		this.nMisure = nMisure;
		this.fMedia = fMedia;
	}

	public LocalDate getDataS() {
		return dataS;
	}

	public void setDataS(LocalDate dataS) {
		this.dataS = dataS;
	}

	public LocalDate getDataE() {
		return dataE;
	}

	public void setDataE(LocalDate dataE) {
		this.dataE = dataE;
	}

	public int getnMisure() {
		return nMisure;
	}

	public void setnMisure(int nMisure) {
		this.nMisure = nMisure;
	}

	public float getfMedia() {
		return fMedia;
	}

	public void setfMedia(float fMedia) {
		this.fMedia = fMedia;
	}
	
	
	

}
