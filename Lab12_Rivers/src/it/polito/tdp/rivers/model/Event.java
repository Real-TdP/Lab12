package it.polito.tdp.rivers.model;

import java.time.LocalDate;


public class Event implements Comparable<Event> {	
	private EventType type;
	private LocalDate data;
	private float flow;
	
	public Event(EventType type, LocalDate data,float flow) {
		this.type = type;
		this.data = data;
		this.flow = flow;
	}

	public EventType getType() {
		return type;
	}

	public LocalDate getData() {
		return data;
	}

	public float getFlow() {
		return flow;
	}

	@Override
	public int compareTo(Event arg0) {
		return this.data.compareTo(arg0.data);
	}
}