package com.etikitcinema.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.etikitcinema.api.models.Showtime;
import com.etikitcinema.api.repositories.ShowtimeRepository;

@Service
public class ShowtimeService {

private final ShowtimeRepository showtimeRepo;
	
	public ShowtimeService(ShowtimeRepository showtimeRepo) {
		this.showtimeRepo = showtimeRepo;
	}
	
	public List<Showtime> getAll(){
		return (List<Showtime>) showtimeRepo.findAll();
	}
	public Showtime findOne(Long id) {
		Optional<Showtime> showtime = showtimeRepo.findById(id);
		return showtime.isPresent() ? showtime.get() : null;
	}
	public Showtime create(Showtime showtime) {
		return showtimeRepo.save(showtime);
	}
	public Showtime edit(Showtime showtime) {
		return showtimeRepo.save(showtime);
	}
	public void deleteShowtime(Long id){
		showtimeRepo.deleteById(id);
	}
}
