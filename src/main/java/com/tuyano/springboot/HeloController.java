package com.tuyano.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HeloController {

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index(Model model) {
		return "index";
	}

	@RequestMapping(value="/", method=RequestMethod.POST)
	public String send(
			@RequestParam(value = "check1", required = false) boolean check1,
			@RequestParam(value = "radio1", required = false) String radio1,
			@RequestParam(value = "select1", required = false) String select1,
			@RequestParam(value = "select2", required = false) String[] select2,
			Model model) {

		String res = "";
		try {
			res = "check:" + check1 +
					" radio:" + radio1 +
					" select:" + select1 +
					" \nselect2:";
		} catch (NullPointerException e) {
			// nothing..
		}
		try {
			res += select2[0];
			for(int i = 1; i < select2.length; i++) {
				res += ", " + select2[i];
			}
		} catch (NullPointerException e) {
			res += "null";
		}
		model.addAttribute("msg", res);
		return "index";
	}

	@RequestMapping("/other")
	public String other() {
		return "redirect:/";
	}

	@RequestMapping("/home")
	public String home() {
		return "forward:/";
	}
}