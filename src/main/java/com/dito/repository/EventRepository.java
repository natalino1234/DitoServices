package com.dito.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dito.entities.Event;

@Repository
public interface EventRepository extends CrudRepository<Event, Long>{
	@Query(value = "select * from EVENT where EVENT = ?1", nativeQuery = true)
    ArrayList<Event> findAutocomplete(String eventName);
}
