package com.tekworks.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.tekworks.entity.Farmer;
import com.tekworks.service.FarmerService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class FarmerController {
	
	@Autowired
	private FarmerService farmerService;
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/getAllFarmers")
	public String getAllFarmers(Model model) {
		model.addAttribute("farmers", farmerService.getAllFarmers());
		model.addAttribute("today",new Date());
		return "show_farmers";
	}
	
	@GetMapping("/addFarmer")
	public String addFarmer(Model model) {
		model.addAttribute(new Farmer());
		return "add_farmer";
	}
	
	@PostMapping("/saveFarmer")
	public String saveFarmer(@Validated Farmer farmer,BindingResult result) { 
		if(result.hasErrors())
			return "add_farmer";
		
		farmerService.saveFarmer(farmer);
		return "redirect:/getAllFarmers";
	}
	
	@GetMapping("/deleteFarmer/{id}")
	public String deleteFarmer(@PathVariable("id") Integer id) { 
		
		
		farmerService.deleteFarmer(id);
		return "redirect:/getAllFarmers";
	}
	@GetMapping("/editFarmer/{id}")
	public String editFarmer(@PathVariable("id") Integer id,Model model) { 
		
		
		model.addAttribute(farmerService.getFarmer(id));
		return "update_farmer";
	}
	
	
	@PostMapping("/updateFarmer")
	public String updateFarmer(@Validated Farmer farmer,BindingResult result) {
		if(result.hasErrors())
			return "update_farmer";
		
		farmerService.updateFarmer(farmer);
		return "redirect:/getAllFarmers";
	}
	
	@PostMapping("/searchByName")
	public String searchByName(HttpServletRequest req,Model model) {
		String name=req.getParameter("farmer_name");
	   model.addAttribute("farmers", farmerService.searchFarmer(name+"%"));
	   model.addAttribute("today",new Date());
		return "show_farmers";
	
	}
	
	
	
	

}
