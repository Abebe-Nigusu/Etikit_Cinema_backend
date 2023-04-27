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

import com.etikitcinema.api.models.Movie;
import com.etikitcinema.api.services.MovieService;

@RestController
@RequestMapping("/")
@CrossOrigin("http://localhost:3000")
public class MovieDBController {
	private final MovieService movieServ;
	
	public MovieDBController(MovieService movieServ) {
		this.movieServ = movieServ;
	}
	@GetMapping("/movie/data")
	public List<Movie> findAllDonations(){
		return movieServ.getAll();
	}
	
	@PostMapping("/movie/data/all")
	public Movie createMovie(
			@RequestParam("title") String title, 
			@RequestParam("duration") int duration, 
			@RequestParam("posterImage") String posterImage){
		Movie newMovie = new Movie(title, duration, posterImage);
		return movieServ.create(newMovie);
	}
	
	@GetMapping("/movie/data/{id}")
	public Movie findOneMovie(@PathVariable("id") Long id) {
		return movieServ.findOne(id);
	}
	@PutMapping("/movie/data/{id}")
	public Movie editOneMovie(
		@PathVariable("id") Long id,
		@RequestParam("title") String title, 
		@RequestParam("duration") int duration, 
		@RequestParam("posterImage") String posterImage){
		Movie foundMovie = movieServ.findOne(id);
		if(foundMovie == null) {
			return foundMovie;
		}
		foundMovie.setTitle(title);
		foundMovie.setDuration(duration);
		foundMovie.setPosterImage(posterImage);
		return movieServ.edit(foundMovie);
	}
	@DeleteMapping("/movie/all/data/{id}")
	public String deleteMovie(@PathVariable("id") Long id) {
		movieServ.deleteMovie(id);
		return id + " has been deleted √";
	}
}
