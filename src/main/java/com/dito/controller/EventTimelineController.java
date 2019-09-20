package com.dito.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
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
import com.dito.entities.Timeline;
import com.dito.entities.TimelineEvent;
import com.dito.exception.EventServiceException;
import com.dito.response.Response;
import com.dito.services.EventTimelineService;

@RestController
@RequestMapping("/api/timeline")
public class EventTimelineController {
	
	@Autowired
	private EventTimelineService timelineService;
	
	@GetMapping
	public ResponseEntity<Timeline> auto_complete() {
		
		String url = "https://storage.googleapis.com/dito-questions/events.json";
		String json = "";
		
		Timeline response = null;
		
		try {
			json = IOUtils.toString(new URL(url));
			JSONObject jsonObj = new JSONObject(json);

			response = new Timeline(timelineService.gerarTimeline(jsonObj));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
