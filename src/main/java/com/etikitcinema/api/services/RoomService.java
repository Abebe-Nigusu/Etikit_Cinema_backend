package com.etikitcinema.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.etikitcinema.api.models.Room;
import com.etikitcinema.api.repositories.RoomRepository;

@Service
public class RoomService {
private final RoomRepository roomRepo;
	
	public RoomService(RoomRepository roomRepo) {
		this.roomRepo = roomRepo;
	}
	
	public List<Room> getAll(){
		return (List<Room>) roomRepo.findAll();
	}
	public Room findOne(Long id) {
		Optional<Room> room = roomRepo.findById(id);
		return room.isPresent() ? room.get() : null;
	}
	public Room create(Room room) {
		return roomRepo.save(room);
	}
	public Room edit(Room room) {
		return roomRepo.save(room);
	}
	public void deleteRoom(Long id){
		roomRepo.deleteById(id);
	}
}
