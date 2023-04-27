package com.etikitcinema.api.controllers;

import java.util.Date;
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

import com.etikitcinema.api.models.Movie;
import com.etikitcinema.api.models.Room;
import com.etikitcinema.api.models.Seat;
import com.etikitcinema.api.models.Showtime;
import com.etikitcinema.api.models.Ticket;
import com.etikitcinema.api.services.SeatService;
import com.etikitcinema.api.services.ShowtimeService;
import com.etikitcinema.api.services.TicketService;

@RestController
@RequestMapping("/")
@CrossOrigin("http://localhost:3000")
public class ShowtimeDBController {
	private final ShowtimeService showtimeServ;
	private final SeatService seatServ;
	private final TicketService ticketServ;
	
	public ShowtimeDBController(ShowtimeService showtimeServ, SeatService seatServ, TicketService ticketServ) {
		this.showtimeServ = showtimeServ;
		this.seatServ = seatServ;
		this.ticketServ = ticketServ;
	}
	@GetMapping("/showtime/data")
	public List<Showtime> findAllDonations(){
		return showtimeServ.getAll();
	}
	
	@PostMapping("/showtime/data/all")
	public Showtime createShowtime(@RequestParam("price") Double price, @RequestParam("startTime") Date startTime, @RequestParam("movie") Movie movie, @RequestParam("room") Room room) {
		Showtime newShowtime = new Showtime(startTime, movie, room);
		Showtime createdShowtime = showtimeServ.create(newShowtime);
		Showtime showtime = showtimeServ.findOne(createdShowtime.getId());
		// create ticket w/: showtime_id, seat_id (iterating through every seat
		List<Seat> seats = seatServ.getAllRoomSeats(room);
		for(Seat seat : seats) {
			Ticket newTicket = new Ticket(price, showtime, seat);
			ticketServ.create(newTicket);
		}
		return newShowtime;
	}
	
	@GetMapping("/showtime/data/{id}")
	public Showtime findOneShowtime(@PathVariable("id") Long id) {
		return showtimeServ.findOne(id);
	}
	@PutMapping("/showtime/data/{id}")
	public Showtime editOneShowtime(
		@PathVariable("id") Long id,
		@RequestParam("startTime") Date startTime){
		Showtime foundShowtime = showtimeServ.findOne(id);
		if(foundShowtime == null) {
			return foundShowtime;
		}
		foundShowtime.setStartTime(startTime);
		return showtimeServ.edit(foundShowtime);
	}
	@DeleteMapping("/showtime/all/data/{id}")
	public String deleteShowtime(@PathVariable("id") Long id) {
		showtimeServ.deleteShowtime(id);
		return id + " has been deleted √";
	}
}