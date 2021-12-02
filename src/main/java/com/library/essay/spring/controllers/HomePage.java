package com.library.essay.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomePage {

	@RequestMapping("/")
	public String welcome() {

		String forwardString = "forward:/pages/homePage.xhtml";
		// String forwardString = "forward:/pages/public/introduction.xhtml";

		System.out.println("HomePage's welcome() is called!");

		return forwardString;
	}
}
