package com.kuangke.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kuangke.domain.MyName;

@Controller
public class hiController {
	@RequestMapping(value="hi")
	public ModelAndView hi(int i){
		ModelAndView model = new ModelAndView();
		model.addObject("msg", "天下无敌强者"+i);
		model.setViewName("hi");
		return model;
	}
	
	@RequestMapping(value="save")	
	public String save1(MyName myname,Map<String, Object> map){
		map.put("myn", myname);
		return "hi";
	}
}
