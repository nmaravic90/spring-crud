package com.nikola.crud.movie;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MovieController {

	@Autowired
	private MovieService moveiService;

	@GetMapping("/movies")
	public String showMovieList(Model model) {
		List<Movie> movieList = moveiService.movieList();
		model.addAttribute("movieList", movieList);
		return "movies";
	}

	@GetMapping("/movie/add-edit")
	public String addNewMovie(Model model) {
		model.addAttribute("movie", new Movie());
		model.addAttribute("pageTitle", "Add new Movie");
		return "add-edit";
	}

	@PostMapping("/movie/save")
	public String saveMovie(Movie movie, RedirectAttributes ra) {
		moveiService.saveMovie(movie);
		ra.addFlashAttribute("message", "The movie has been saved successfully!");
		return "redirect:../movies";
	}

	@GetMapping("/movie/edit/{id}")
	public String editMovie(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
		try {
			Movie movie = moveiService.getMovie(id);
			model.addAttribute("movie", movie);
			model.addAttribute("pageTitle", "Edit Movie");
			return "add-edit";

		} catch (MovieNotFoundException e) {
			ra.addFlashAttribute("message", e.getMessage());
			return "redirect:../movies";
		}
	}
	
	@GetMapping("/movie/delete/{id}")
	public String deleteMovie(@PathVariable("id") Integer id, RedirectAttributes ra) {
		try {
			moveiService.deleteMovie(id);
			ra.addFlashAttribute("message", "The move has been successfully deleted!");
		} catch (MovieNotFoundException e) {
			ra.addFlashAttribute("message", e.getMessage());
			return "redirect:../../movies";
		}
		return "redirect:../../movies";
	}

}
