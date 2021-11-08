package org.dragon.yunpeng.spring.controllers;

import org.springframework.web.bind.annotation.RequestMapping;

public class HomePage {

	@RequestMapping("/SpringBoot_Primefaces_EssayLibrary")
	public String welcome() {
	 
	    return "hello.xhtml";
	}
}
