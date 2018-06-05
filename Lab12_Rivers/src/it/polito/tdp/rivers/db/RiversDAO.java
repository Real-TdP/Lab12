package it.polito.tdp.rivers.db;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.rivers.model.Analisi;
import it.polito.tdp.rivers.model.Event;
import it.polito.tdp.rivers.model.EventType;
import it.polito.tdp.rivers.model.River;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RiversDAO {

	public List<River> getAllRivers() {
		
		final String sql = "SELECT id, name FROM river";

		List<River> rivers = new LinkedList<River>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				rivers.add(new River(res.getInt("id"), res.getString("name")));
			}

			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return rivers;
	}
	
	
	public Analisi takeAnalisys(int idRiver) {
		
		final String sql = "SELECT 	min(day),max(day),count(*),avg(fl.flow) FROM flow as fl WHERE river=?";
		Analisi a=null;

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, idRiver);
			ResultSet res = st.executeQuery();

			if(res.next()) {
				
				a= new Analisi(res.getDate("min(day)").toLocalDate(),res.getDate("max(day)").toLocalDate(),res.getInt("count(*)"),res.getFloat("avg(fl.flow)"));
			}

			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return a;
	}


	public List<Event> getAllEvents(River river) {
		final String sql = "SELECT day,flow FROM flow as fl WHERE river=?";
		List<Event> le=new ArrayList<Event>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1,river.getId());
			ResultSet res = st.executeQuery();

			while(res.next()) {
				
				Event e= new Event(EventType.INIZIO_DAY,res.getDate("day").toLocalDate(),res.getFloat("flow"));
				le.add(e);
			}

			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return le;
	}
}
