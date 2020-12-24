package com.kuangke.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MrZhangController {
	@RequestMapping(value="modify")
	public void modify(){
		System.out.println("---------modify----------");
	}
}
