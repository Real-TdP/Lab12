package it.polito.tdp.rivers.model;

import java.util.List;
import java.util.PriorityQueue;

public class Simulazione {
	
	private float c;       //CAPIENZA
	private float fOutMin; //FLUSSO MINIMO IN USCITA
	private float q;   //DIMENSIONI BACINO   q=k*fmed*30giorni =k*fmed*3600*24*30=2592000*k*fmed
	private PriorityQueue<Event> pq;
	
	
	public Simulazione(List<Event> events,float k,float fMed) {
		this.q=k*fMed*3600*24*30;
		this.c = q/2;
		this.fOutMin = (float) (fMed*0.8);
		pq= new PriorityQueue<Event>(events);
	}
	
	public Statistiche run() {
		Statistiche sta = new Statistiche();
		Event e;
		while((e=pq.poll())!=null) {
			this.processEvent(e,sta);
		}
		return sta;
	}
	
	public void processEvent(Event e,Statistiche sta) {
		switch(e.getType()) {

		case INIZIO_DAY:
			float fOut=this.fOutMin;
			float fIn=e.getFlow();
			if(Math.random()>=0.95)
				fOut+=10*fOut;
			
			c+=(fIn-fOut)*3600*24;		
				if(c>q)
					c=q; //TRACIMAZIONE
				if(c<=0) {
					c=0;
					sta.upNoWat();
				}
				sta.upcMed(c);
			break;

		}
		
	}

}
