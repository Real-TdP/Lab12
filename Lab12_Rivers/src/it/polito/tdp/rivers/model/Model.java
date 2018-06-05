package it.polito.tdp.rivers.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.rivers.db.RiversDAO;

public class Model {
	private List<River> river;
	private RiversDAO rDAO;
	
	
	
	public Model() {
		this.rDAO= new RiversDAO();
	}




	public List<River> getRiver(){
		try {
			this.river= new ArrayList<River>(rDAO.getAllRivers());
		} catch (RuntimeException e) {
			river=null;
		}
		return river;
	}




	public Analisi analyze(River value) {
		Analisi a=rDAO.takeAnalisys(value.getId());
		return a;
	}
	
	public Statistiche simula(River river,float k) {
		Analisi a=this.analyze(river);
		Simulazione sim = new Simulazione(rDAO.getAllEvents(river),k,a.getfMedia());
		Statistiche s=sim.run();
		return s;
		
	}


}
