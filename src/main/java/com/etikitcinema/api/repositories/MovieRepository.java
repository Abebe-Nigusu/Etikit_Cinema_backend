package com.etikitcinema.api.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.etikitcinema.api.models.Movie;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Long>{

}
