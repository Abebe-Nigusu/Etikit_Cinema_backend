package com.etikitcinema.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.etikitcinema.api.models.Movie;
import com.etikitcinema.api.repositories.MovieRepository;

@Service
public class MovieService {
private final MovieRepository movieRepo;
	
	public MovieService(MovieRepository movieRepo) {
		this.movieRepo = movieRepo;
	}
	
	public List<Movie> getAll(){
		return (List<Movie>) movieRepo.findAll();
	}
	public Movie findOne(Long id) {
		Optional<Movie> movie = movieRepo.findById(id);
		return movie.isPresent() ? movie.get() : null;
	}
	public Movie create(Movie movie) {
		return movieRepo.save(movie);
	}
	public Movie edit(Movie movie) {
		return movieRepo.save(movie);
	}
	public void deleteMovie(Long id){
		movieRepo.deleteById(id);
	}

}
