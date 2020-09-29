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
		model.addAttribute("msg", "お名前を書いて送信してください。");
		return "index";
	}

	@RequestMapping(value="/", method=RequestMethod.POST)
	public String send(@RequestParam("text1") String str, Model model) {
		model.addAttribute("msg", "こんにちは、 " + str + "さん！");
		return "index";
	}
}