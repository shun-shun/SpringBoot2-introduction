package com.tuyano.springboot;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tuyano.springboot.repositories.MyDataRepository;

@Controller
public class HeloController {

	@Autowired
	MyDataRepository repository;

	@PostConstruct
	public void init() {
		MyData d1 = new MyData();
		d1.setName("tuyano");
		d1.setAge(123);
		d1.setMail("syoda@tuyano.com");
		d1.setMemo("this is my data!");
		repository.saveAndFlush(d1);
		MyData d2 = new MyData();
		d2.setName("hanako");
		d2.setAge(15);
		d2.setMail("hanako@flower");
		d2.setMemo("my girl friend.");
		repository.saveAndFlush(d2);
		MyData d3 = new MyData();
		d3.setName("sachiko");
		d3.setAge(37);
		d3.setMail("sachico@happy");
		d3.setMemo("my work friend...");
		repository.saveAndFlush(d3);
	}

	@RequestMapping(value="/", method = RequestMethod.GET)
	public String index(@ModelAttribute("formModel") MyData myData, Model model) {
		model.addAttribute("msg", "this is sample content.");
		Iterable<MyData> list = repository.findAll();
		model.addAttribute("datalist", list);
		return "index";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	@Transactional(readOnly = false)
	public String form(@ModelAttribute("formModel") MyData myData, Model model) {
		repository.saveAndFlush(myData);
		return "redirect:/";
	}

}