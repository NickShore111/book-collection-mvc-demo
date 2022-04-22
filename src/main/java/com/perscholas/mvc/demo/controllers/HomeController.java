package com.perscholas.mvc.demo.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.perscholas.mvc.demo.models.Book;
import com.perscholas.mvc.demo.services.BookService;

@Controller
public class HomeController {

	private final BookService bookService;

	public HomeController(BookService bookService) {
		this.bookService = bookService;
	}

	@RequestMapping("/books")
	public String allBooks(Model model) {
		model.addAttribute("books", bookService.allBooks());
		Book book = new Book();
		model.addAttribute("book", book);
		return "index";
	}

	// @RequestMapping(value="/insertBook", method=RequestMethod.POST)
//	@PostMapping("/insertBook")
//	public String insertBook(@RequestParam("title") String title, @RequestParam("description") String description,
//			@RequestParam("language") String language, @RequestParam("numberOfPages") Integer pages) {
//		Book book = new Book(title, description, language, pages);
//		bookService.createBook(book);
//		return "redirect:/books";
//	}
	@PostMapping("/books")
	public String insertBook(@Valid Book book, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("books", bookService.allBooks());
			return "index";
		} else {
			// model.addAttribute("book", book);
			bookService.createBook(book);
			return "redirect:/books";
		}
	}
}
