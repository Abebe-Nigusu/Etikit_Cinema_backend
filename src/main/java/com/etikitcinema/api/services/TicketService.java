package com.etikitcinema.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.etikitcinema.api.models.Ticket;
import com.etikitcinema.api.repositories.TicketRepository;

@Service
public class TicketService {

private final TicketRepository ticketRepo;
	
	public TicketService(TicketRepository ticketRepo) {
		this.ticketRepo = ticketRepo;
	}
	
	public List<Ticket> getAll(){
		return (List<Ticket>) ticketRepo.findAll();
	}
	public Ticket findOne(Long id) {
		Optional<Ticket> ticket = ticketRepo.findById(id);
		return ticket.isPresent() ? ticket.get() : null;
	}
	public Ticket create(Ticket ticket) {
		return ticketRepo.save(ticket);
	}
	public Ticket edit(Ticket ticket) {
		return ticketRepo.save(ticket);
	}
	public void deleteTicket(Long id){
		ticketRepo.deleteById(id);
	}
}
