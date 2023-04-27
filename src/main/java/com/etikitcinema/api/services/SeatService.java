package com.etikitcinema.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.etikitcinema.api.models.Room;
import com.etikitcinema.api.models.Seat;
import com.etikitcinema.api.repositories.SeatRepository;

@Service
public class SeatService {
private final SeatRepository seatRepo;
	
	public SeatService(SeatRepository seatRepo) {
		this.seatRepo = seatRepo;
	}
	
	public List<Seat> getAll(){
		return (List<Seat>) seatRepo.findAll();
	}
	public Seat findOne(Long id) {
		Optional<Seat> seat = seatRepo.findById(id);
		return seat.isPresent() ? seat.get() : null;
	}
	public List<Seat> getAllRoomSeats(Room room){
		return seatRepo.findByRoomContaining(room);
	}
	public Seat create(Seat seat) {
		return seatRepo.save(seat);
	}
	public Seat edit(Seat seat) {
		return seatRepo.save(seat);
	}
	public void deleteSeat(Long id){
		seatRepo.deleteById(id);
	}
}
