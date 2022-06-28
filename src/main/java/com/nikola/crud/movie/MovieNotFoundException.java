package com.nikola.crud.movie;

@SuppressWarnings("serial")
public class MovieNotFoundException extends Throwable {

	public MovieNotFoundException(String message) {
		super(message);
	}
}
