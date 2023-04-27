package com.etikitcinema.api.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.etikitcinema.api.models.Ticket;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long>{

}
