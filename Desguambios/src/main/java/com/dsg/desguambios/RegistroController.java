package com.dsg.desguambios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class RegistroController {

	@Autowired
	 private UserService usersService;
	 
	 
	 /*
	 @RequestMapping(value = "/registro", method=RequestMethod.GET)
	 public String registro(Model model) {
		
		 
		 
		return "registro";
		 
		 
		 
	 }
	 */
	 
	 
	 @RequestMapping(value = "/registro")
	 public String registro(Model model,@RequestParam String alta, String usuario,String email,
			  String direccion,  String password,  String valPassword) {
		
		if(Integer.parseInt(alta)==1) {
			lista
		}
		/*
		model.addAttribute("usuario", usuario);
		model.addAttribute("email", email);
		model.addAttribute("direccion", direccion);
		model.addAttribute("password", password);
		model.addAttribute("valPassword", valPassword);
		*/
		
		System.out.println("++++++++++++++++++++++++"); 
		return "prueba";
		
		 
		 
		 
	 } 
	 
}
