package it.polito.tdp.rivers.model;

public class Statistiche {
	private float cMed;
	private int noWat;
	private int cont;
	
	
	public Statistiche() {
		noWat=0;
		cMed=0;
		cont=0;
	}


	public float getcMed() {
		return cMed;
	}


	public void setcMed(float cMed) {
		this.cMed = cMed;
	}


	public int getNoWat() {
		return noWat;
	}


	public void setNoWat(int noWat) {
		this.noWat = noWat;
	}
	
	public void upNoWat() {
		this.noWat++;
	}
	
	public void upcMed(float cMed) {
		this.cMed += cMed;
		cont++;
	}
	
	public float getCapMedia() {
		return cMed/cont;
	}
	

}
