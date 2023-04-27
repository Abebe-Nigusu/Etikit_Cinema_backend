package com.etikitcinema.api.models;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity // declares that it is going to be apart of mysql
@Table(name = "movies") // gives the table name of the model
public class Movie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // generates an auto incrementing 
	private Long id;
	
	@NotEmpty(message = "Title is required!") // validation for strings
	private String title;
	
	@NotNull(message = "Description is required!")
	@Min(value = 15, message="Duration must be longer than 15 minutes!")
	private int duration;
	
	@NotNull(message = "Poster image is required!") // validation for ints
	private String posterImage;
	
	@OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
	private List<Showtime> showtimes;

    @Column(updatable = false)
	private Date createdAt;

	private Date updatedAt;

    // empty constructor
	public Movie() {
	}

    public Movie(String title, int duration, String posterImage) {
		
		this.title = title;
		this.duration = duration;
		this.posterImage = posterImage;
		
	}
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getPosterImage() {
		return posterImage;
	}

	public void setPosterImage(String posterImage) {
		this.posterImage = posterImage;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	@PrePersist // adds the created at date and time to sql on creation 
	protected void onCreate() {
		this.createdAt = new Date();
	}

	@PreUpdate // add the updated at date and time when being updated
	protected void onUpdate() {
		this.updatedAt = new Date();
	}
}