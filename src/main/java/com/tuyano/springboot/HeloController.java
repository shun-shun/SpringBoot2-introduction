package com.tuyano.springboot;


import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tuyano.springboot.repositories.MyDataRepository;

@Controller
public class HeloController {

	@Autowired
	MyDataRepository repository;

	@PersistenceContext
	EntityManager entityManager;

	MyDataDaoImpl dao;

	@PostConstruct
	public void init() {
		dao = new MyDataDaoImpl(entityManager);

		MyData d1 = new MyData();
		d1.setName("tuyano");
		d1.setAge(123);
		d1.setMail("syoda@tuyano.com");
		d1.setMemo("090999999");
		repository.saveAndFlush(d1);
		MyData d2 = new MyData();
		d2.setName("hanako");
		d2.setAge(15);
		d2.setMail("hanako@flower");
		d2.setMemo("080888888");
		repository.saveAndFlush(d2);
		MyData d3 = new MyData();
		d3.setName("sachiko");
		d3.setAge(37);
		d3.setMail("sachico@happy");
		d3.setMemo("070777777");
		repository.saveAndFlush(d3);
	}

	@RequestMapping(value="/", method = RequestMethod.GET)
	public String index(@ModelAttribute("formModel") MyData myData, Model model) {
		model.addAttribute("msg", "MyDataのサンプルです。");
		Iterable<MyData> list = dao.getAll();
		model.addAttribute("datalist", list);
		return "index";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	@Transactional(readOnly = false)
	public String form(@ModelAttribute("formModel") @Validated MyData myData, BindingResult result, Model model) {
		if(!result.hasErrors()) {
			repository.saveAndFlush(myData);
			return "redirect:/";
		}
		model.addAttribute("msg", "sorry, error is occured...");
		Iterable<MyData> list = repository.findAll();
		model.addAttribute("datalist", list);
		return "index";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@ModelAttribute MyData myData, @PathVariable int id, Model model) {
		model.addAttribute("title", "edit mydata.");
		Optional<MyData> data = repository.findById((long)id);
		model.addAttribute("formModel", data.get());
		return "edit";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable int id, Model model) {
		model.addAttribute("title", "delete mydata.");
		Optional<MyData> data = repository.findById((long)id);
		model.addAttribute("formModel", data.get());
		return "delete";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@Transactional(readOnly = false)
	public String remove(@RequestParam long id, Model model) {
		repository.deleteById(id);
		return "redirect:/";
	}
}