package com.etikitcinema.api.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.etikitcinema.api.models.Ticket;
import com.etikitcinema.api.services.TicketService;

@RestController
@RequestMapping("/")
public class TicketDBController {
	private final TicketService ticketServ;
	
	public TicketDBController(TicketService ticketServ) {
		this.ticketServ = ticketServ;
	}
	@GetMapping("/ticket/data")
	public List<Ticket> findAllDonations(){
		return ticketServ.getAll();
	}
	
	@PostMapping("/ticket/data/all")
	public Ticket createTicket(
			@RequestParam("price") Double price,
			@RequestParam("purchaseDate") Date purchaseDate) {
		Ticket newTicket = new Ticket(price, purchaseDate);
		return ticketServ.create(newTicket);
	}
	
	@GetMapping("/ticket/data/{id}")
	public Ticket findOneTicket(@PathVariable("id") Long id) {
		return ticketServ.findOne(id);
	}
	@PutMapping("/ticket/data/{id}")
	public Ticket editOneTicket(
		@PathVariable("id") Long id,
		@RequestParam("price") Double price,
		@RequestParam("purchaseDate") Date purchaseDate) {
		Ticket foundTicket = ticketServ.findOne(id);
		if(foundTicket == null) {
			return foundTicket;
		}
		foundTicket.setPurchaseDate(purchaseDate);
		return ticketServ.edit(foundTicket);
	}
	@DeleteMapping("/ticket/all/data/{id}")
	public String deleteTicket(@PathVariable("id") Long id) {
		ticketServ.deleteTicket(id);
		return id + " has been deleted √";
	}
}