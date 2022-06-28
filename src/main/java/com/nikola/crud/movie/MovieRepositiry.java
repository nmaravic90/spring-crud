package com.nikola.crud.movie;

import org.springframework.data.repository.CrudRepository;

public interface MovieRepositiry extends CrudRepository<Movie, Integer> {

	// count entities with specific name
	// Spring Data 1.7.1.RELEASE
	public Long countById(Integer id);

}
