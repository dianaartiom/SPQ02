package com.deusto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.deusto.model.Book;
import com.deusto.repository.BookRepository;

@Controller
public class IndexController {

	@Autowired
	BookRepository bRepository;
	
	@PostMapping(path = "/")
	public ModelAndView post(@ModelAttribute Book book, Model model){
		bRepository.insert(book);
		model.addAttribute("content", bRepository.findAll());
		return new ModelAndView("list");
	}
	
    @GetMapping(path = "/")
    public ModelAndView index(Model model) {
    	Book bok = new Book();
    	bok.setTitle("not use this");
    	bok.setAuthor("not use this");
    	bRepository.insert(bok);
        return new ModelAndView("index","book", new Book());
    }
    
    @GetMapping(path = "/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<?> json(){
    	return new ResponseEntity(bRepository.findByTitle("not use this"),HttpStatus.OK);
    }
}