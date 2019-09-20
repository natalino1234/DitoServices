package com.dito.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dito.entities.Event;

@Repository
public interface EventRepository extends CrudRepository<Event, Long>{
	@Query(value = "select event, count(event) from EVENT where EVENT like concat('%',?1) group by count(event) order by count(event) desc LIMIT ?2", nativeQuery = true)
    ArrayList<Event> findAutocomplete(String eventName, int maisOcorridos);
}
