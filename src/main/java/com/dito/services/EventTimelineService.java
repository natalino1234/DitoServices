package com.dito.services;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.dito.entities.Product;
import com.dito.entities.Timeline;
import com.dito.entities.TimelineEvent;

@Service
public class EventTimelineService {

	public List<TimelineEvent> gerarTimeline(JSONObject json) {
		
		List<TimelineEvent> timeline = new ArrayList<TimelineEvent>();
		
		JSONArray raiz = (JSONArray) json.get("events");
		raiz.forEach(item -> {
				JSONObject obj = (JSONObject) item;
				String event = obj.get("event").toString();
				if(event.equals("comprou")) {
					JSONArray prods = (JSONArray) obj.get("custom_data");
					
					TimelineEvent e = new TimelineEvent();
					e.setRevenue(obj.getDouble("revenue"));
					try {
						e.setTimestamp(obj.getString("timestamp"));
					} catch (JSONException e1) {
						e1.printStackTrace();
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					
					for (int i = 0; i < prods.length(); i++) {
						JSONObject data = (JSONObject) prods.get(i);
						String key = data.getString("key");
						if(key.equals("store_name")) {
							e.setStore_name(data.getString("value"));
						}else if(key.equals("transaction_id")) {
							e.setTransaction(data.getString("value"));
						}
					}
					
					if(!eventExists(timeline,e)) {
						timeline.add(e);
					}else {
						TimelineEvent eventFound = search(timeline, e.getTransaction());
						eventFound.setStore_name(e.getStore_name());
						try {
							eventFound.setTimestamp(e.getTimestamp());
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						eventFound.setRevenue(e.getRevenue());
					}
					
				}else if(event.equals("comprou-produto")) {
					JSONArray prods = (JSONArray) obj.get("custom_data");
					
					Product p = new Product();
					
					for (int i = 0; i < prods.length(); i++) {
						JSONObject data = (JSONObject) prods.get(i);
						String key = data.getString("key");
						if(key.equals("product_name")) {
							p.setName(data.getString("value"));
						}else if(key.equals("transaction_id")) {
							TimelineEvent eventFound = search(timeline, data.getString("value"));
							eventFound.getProducts().add(p);
						}else if(key.equals("product_price")) {
							p.setPrice(data.getDouble("value"));
						}
					}
				}
			});
		
		JSONObject result = new JSONObject();
		result.append("timeline", timeline);
		
		Collections.sort(timeline);
		
		return timeline;
	}
	
	/*
	 * Procurar evento dentro da timeline, caso não exista, criar ele.
	 * 
	 * @param events Lista de eventos
	 * @param transaction transaction id
	 */
	
	private TimelineEvent search(List<TimelineEvent> events, String transaction) {
		
		for(TimelineEvent e : events) {
			if(e.getTransaction().equals(transaction)) {
				System.err.println("Encontrado: "+e);
				return e;
			}
		}
		System.err.println("Não encontrado: "+transaction);
		
		TimelineEvent event = new TimelineEvent();
		event.setTransaction(transaction);
		events.add(event);
		return event;
	}

	
	/*
	 * Verificar se o evento já existe a partir de sua transaction id
	 * 
	 * @param events Lista de eventos
	 * @param event transaction id
	 */
	
	private boolean eventExists(List<TimelineEvent> events, TimelineEvent event) {
		
		for(TimelineEvent e : events) {
			if(e.getTransaction().equals(event.getTransaction())) {
				return true;
			}
		}
		return false;
	}
}