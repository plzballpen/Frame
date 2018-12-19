package com.hr.test.web;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hr.test.service.PostsService;

@Controller
@AllArgsConstructor
public class WebController {

	private PostsService postsService;
	
    @GetMapping("/")
    public String main(Model model) {
    	model.addAttribute("posts", postsService.findAllDesc());
        return "main";
    }
}