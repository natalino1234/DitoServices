package com.dito.dtos;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class EventDto implements Serializable {

	private static final long serialVersionUID = 8132268928836526537L;

	private Long id;
	
	private String event;

	private Date timestamp;

	public EventDto() {

	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@NotNull(message = "O Titulo da task é uma informação obrigatória")
	@Length(min = 3, max = 40, message = "O Titulo da task deve possuir entre 3 e 40 caracteres")
	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "EventDto [id=" + id + ", event=" + event + ", timestamp=" + timestamp + "]";
	}
	
}