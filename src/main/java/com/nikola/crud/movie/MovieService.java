package com.nikola.crud.movie;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
	
	@Autowired 
	private MovieRepositiry movieRepo;
	
	public List<Movie> movieList(){
		return (List<Movie>) movieRepo.findAll();
	}

	public void saveMovie(Movie movie) {
		movieRepo.save(movie);
	}
	
	public Movie getMovie(Integer id) throws MovieNotFoundException {
		Optional<Movie> result = movieRepo.findById(id);
		if(result.isPresent()) {
			return result.get();
		}
		throw new MovieNotFoundException("Couldn't find any movies with ID: " + id);
	}
	
	public void deleteMovie(Integer id) throws MovieNotFoundException {
		Long count = movieRepo.countById(id);
		if(count == null || count == 0) {
			throw new MovieNotFoundException("Couldn't find any movies with ID: " + id);
		}
		movieRepo.deleteById(id);
	}

}
