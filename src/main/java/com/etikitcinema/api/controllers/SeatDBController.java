package com.etikitcinema.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.etikitcinema.api.models.Room;
import com.etikitcinema.api.models.Seat;
import com.etikitcinema.api.services.RoomService;
import com.etikitcinema.api.services.SeatService;

@RestController
@RequestMapping("/")
@CrossOrigin("http://localhost:3000")
public class SeatDBController {
	private final SeatService seatServ;
	private final RoomService roomServ;
	
	public SeatDBController(SeatService seatServ, RoomService roomServ) {
		this.seatServ = seatServ;
		this.roomServ = roomServ;
	}
	@GetMapping("/seat/data")
	public List<Seat> findAllRoomSeats(){
		return seatServ.getAll();
	}
	
	@PostMapping("/seat/data/all")
	public Seat createSeat(
			@RequestParam("rowChar") String rowChar, 
			@RequestParam("seatNumber") Integer seatNumber,
			@RequestParam("room_id") Long room_id){
		Room room = roomServ.findOne(room_id);
		Seat newSeat = new Seat(rowChar, seatNumber, room);
		return seatServ.create(newSeat);
	}
	
	@GetMapping("/seat/data/{id}")
	public Seat findOneSeat(@PathVariable("id") Long id) {
		return seatServ.findOne(id);
	}
	@PutMapping("/seat/data/{id}")
	public Seat editOneSeat(
		@PathVariable("id") Long id,
		@RequestParam("rowChar") String rowChar, 
		@RequestParam("seatNumber") Integer seatNumber,
		@RequestParam("room") Room room){
		Seat foundSeat = seatServ.findOne(id);
		if(foundSeat == null) {
			return foundSeat;
		}
		foundSeat.setRow(rowChar);
		foundSeat.setSeatNumber(seatNumber);
		return seatServ.edit(foundSeat);
	}
	@DeleteMapping("/seat/all/data/{id}")
	public String deleteSeat(@PathVariable("id") Long id) {
		seatServ.deleteSeat(id);
		return id + " has been deleted √";
	}
}