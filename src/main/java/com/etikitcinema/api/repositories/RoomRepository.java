package com.etikitcinema.api.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.etikitcinema.api.models.Room;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long>{

}
