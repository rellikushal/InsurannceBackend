package com.insurance.www.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.www.model.PremiumCaliculation;
import com.insurance.www.service.PremiumCaliculationService;

@RestController
@RequestMapping(path = "/api/premium/")
@CrossOrigin(origins="*")

public class PremiumCaliculationController 
{
	@Autowired
	PremiumCaliculationService premiumCaliculationService;
	
	@GetMapping("/premiumAmount/{id}")
	public  PremiumCaliculation CaliculatePremiumAmount(@PathVariable long id)
	{
		return premiumCaliculationService.CaliculatePremiumAmount(id);
	}
	@PostMapping("/addPremiumDetails")
	public String AddPremiumDetails(@RequestBody PremiumCaliculation data)
	{
		return premiumCaliculationService.AddPremiumDetails(data);
	}
	
	@GetMapping("/premiumAmount/fetch/{id}")
	public  double CaliculatePremiumAmount1(@PathVariable long id)
	{
		return premiumCaliculationService.CaliculatePremiumAmount1(id);
	}
	
	@GetMapping("/state")
	public  PremiumCaliculation state()
	{
		return premiumCaliculationService.state();
	}
	
	@GetMapping("/selectedId{id}")
	public void selectedId(@PathVariable long id)
	{
		premiumCaliculationService.selectedId(id);
	}
	
	@PostMapping(path = "/caliculatePremium",consumes = "application/json")
	public int calculatePremiumAmountforAdmin(@RequestBody PremiumCaliculation request ) 
	{
		return premiumCaliculationService.calculatePremiumAmountforAdmin(request);
	}
	
	@PutMapping("/changePremiumCalicution/values/{id}")
	public String changePremiumCaliculationvalues(@PathVariable long id,@RequestBody PremiumCaliculation request)
	{
		return premiumCaliculationService.changePremiumCaliculationvalues(id,request);
	}
	
	@GetMapping("/getAll/premiumCaliculation/values")
	public List<PremiumCaliculation> getAllPremiumDetails()
	{
		return premiumCaliculationService.getAllPremiumDetails();
	}
}
