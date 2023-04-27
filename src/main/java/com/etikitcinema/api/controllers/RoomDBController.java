package com.etikitcinema.api.controllers;

import java.util.Arrays;
import java.util.List;

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
public class RoomDBController {
	private final RoomService roomServ;
	private final SeatService seatServ;
	
	public RoomDBController(RoomService roomServ, SeatService seatServ) {
		this.roomServ = roomServ;
		this.seatServ = seatServ;
	}
	@GetMapping("/room/data")
	public List<Room> findAllDonations(){
		return roomServ.getAll();
	}
	
	@PostMapping("/room/data/all")
	public Room createRoom(
	        @RequestParam("name") String name,
	        @RequestParam("totalSeats") int totalSeats) {
	    Room newRoom = new Room(name, totalSeats);
	    Room createdRoom = roomServ.create(newRoom); // Modify the create method to return the created room object
	    Room room = roomServ.findOne(createdRoom.getId()); // Use the ID from the created room object
	    List<String> letters = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j");
	    for (int i = 0; i < letters.size(); i++) {
	        for (Integer j = 1; j <= 10; j++) {
	        	Seat seat = new Seat(letters.get(i), j, room);
	            seatServ.create(seat); // Use the ID from the created room object
	        }
	    }
	    return room;
	}

	@GetMapping("/room/data/{id}")
	public Room findOneRoom(@PathVariable("id") Long id) {
		return roomServ.findOne(id);
	}
	@PutMapping("/room/data/{id}")
	public Room editOneRoom(
		@PathVariable("id") Long id,
		@RequestParam("name") String name, 
		@RequestParam("totalSeats") int totalSeats){
		Room foundRoom = roomServ.findOne(id);
		if(foundRoom == null) {
			return foundRoom;
		}
		foundRoom.setName(name);
		foundRoom.setTotalSeats(totalSeats);
		return roomServ.edit(foundRoom);
	}
	@DeleteMapping("/room/all/data/{id}")
	public String deleteRoom(@PathVariable("id") Long id) {
		roomServ.deleteRoom(id);
		return id + " has been deleted √";
	}
}
