package com.dito.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dito.dtos.EventDto;
import com.dito.entities.Event;
import com.dito.exception.EventServiceException;
import com.dito.response.Response;
import com.dito.services.EventService;

@RestController
@RequestMapping("/api/event")
public class EventController {
	
	@Autowired
	private EventService eventService;
	
	@PostMapping
	public ResponseEntity<Response<Event>> create(@Valid @RequestBody EventDto eventDto, BindingResult result){
		Response<Event> response = new Response<Event>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		Event eventSalva = this.eventService.salvar(eventDto);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(eventDto.getId())
				.toUri();
		response.setData(eventSalva);
		return ResponseEntity.created(location).body(response);
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Response<Event>> buscar(@PathVariable("id") Long id) {
		  
		Event event;
		try {
			event = eventService.buscar(id);
		} catch (EventServiceException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		Response<Event> response = new Response<Event>();
		response.setData(event);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@GetMapping
	public ResponseEntity<List<Event>> listar() {
		List<Event> viagens = eventService.listar();
		return ResponseEntity.status(HttpStatus.OK).body(viagens);
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Response<Event>> delete(@PathVariable("id") Long id) {
		  
		Event event;
		try {
			event = eventService.buscar(id);
		} catch (EventServiceException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		Response<Event> response = new Response<Event>();
		response.setData(event);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@PutMapping(path = "/{id}")
	public ResponseEntity<Response<Event>> editar(@PathVariable("id") Long id, @Valid @RequestBody EventDto eventDto, BindingResult result) {
		Response<Event> response = new Response<Event>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		Event eventSalva = null;
		try {
			eventSalva = this.eventService.editar(id, eventDto);
		} catch (EventServiceException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(eventDto.getId())
				.toUri();
		response.setData(eventSalva);
		return ResponseEntity.created(location).body(response);
	}
	
	@GetMapping(path = "/autocomplete/{texto}")
	public ResponseEntity<List<Event>> auto_complete(@PathVariable("texto") String texto) {
		
		if(texto.length() < 2){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		
		List<Event> events;
		try {
			events = eventService.buscar(texto);
		} catch (EventServiceException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.status(HttpStatus.OK).body(events);
	}
}
