package com.dito.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import com.dito.dtos.EventDto;
import com.dito.entities.Event;
import com.dito.exception.EventServiceException;
import com.dito.repository.EventRepository;

@Service
@EnableJpaRepositories("com.dito.repository")
@EntityScan("com.dito.entities")
public class EventService {

	@Autowired
	private EventRepository eventRepository;
	
	private final int QTD_EVENTOS_MAIS_OCORRIDOS = 5;

	public List<Event> listar() {
		List<Event> events = new ArrayList<Event>();
		eventRepository.findAll().forEach(events::add);
		return events;
	}

	public Event salvar(EventDto eventDto) {
		Event event = new Event();

		event.setEvent(eventDto.getEvent());
		event.setTimestamp(eventDto.getTimestamp());
		eventRepository.save(event);
		return event;
	}

	public Event buscar(Long id) throws EventServiceException {
		Event event = eventRepository.findOne(id);

		if (event == null) {
			throw new EventServiceException("Não existe esta event cadastrada");
		}
		return event;
	}
	
	public List<Event> buscar(String texto) throws EventServiceException {
		List<Event> event = eventRepository.findAutocomplete(texto,QTD_EVENTOS_MAIS_OCORRIDOS);

		if (event == null) {
			throw new EventServiceException("Não existe esta event cadastrada");
		}
		return event;
	}
	
	public Event deletar(Long id) throws EventServiceException {
		Event event = eventRepository.findOne(id);
		eventRepository.delete(event);
		return event;
	}

	public Event editar(Long id, EventDto eventDto) throws EventServiceException {
		Event event = eventRepository.findOne(id);
		
		if (event == null) {
			throw new EventServiceException("Não existe esta event cadastrada");
		}
		
		event.setEvent(eventDto.getEvent());
		event.setTimestamp(eventDto.getTimestamp());
		eventRepository.save(event);
		return event;
	}
}