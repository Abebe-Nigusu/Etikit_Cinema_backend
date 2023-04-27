package com.etikitcinema.api.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.etikitcinema.api.models.Showtime;

@Repository
public interface ShowtimeRepository extends CrudRepository<Showtime, Long>{

}
