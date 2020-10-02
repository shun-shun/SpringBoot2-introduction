package com.tuyano.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tuyano.springboot.repositories.MyDataRepository;

@Controller
public class HeloController {

	@Autowired
	MyDataRepository repository;

	@RequestMapping(value="/")
	public String index(Model model) {
		model.addAttribute("msg", "this is sample content.");
		Iterable<MyData> list = repository.findAll();
		model.addAttribute("data", list);
		return "index";
	}

}