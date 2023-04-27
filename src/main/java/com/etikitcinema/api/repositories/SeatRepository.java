package com.etikitcinema.api.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.etikitcinema.api.models.Room;
import com.etikitcinema.api.models.Seat;

@Repository
public interface SeatRepository extends CrudRepository<Seat, Long>{
	List<Seat> findByRoomContaining(Room room);
	
}
