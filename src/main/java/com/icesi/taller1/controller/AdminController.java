package com.icesi.taller1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.icesi.taller1.model.Address;
import com.icesi.taller1.model.BasicInfo;
import com.icesi.taller1.model.Countryregion;
import com.icesi.taller1.model.Salestaxrate;
import com.icesi.taller1.model.Stateprovince;
import com.icesi.taller1.service.AddressService;
import com.icesi.taller1.service.CountryregionService;
import com.icesi.taller1.service.SalestaxrateService;
import com.icesi.taller1.service.StateprovinceService;

@Controller
public class AdminController {
	
	private CountryregionService countryregionService;
	private SalestaxrateService salestaxrateService;
	private StateprovinceService stateprovinceService;
	
	@Autowired
	public AdminController(CountryregionService countryregionService,SalestaxrateService salestaxrateService,StateprovinceService stateprovinceService) {
		this.countryregionService = countryregionService;
		this.salestaxrateService = salestaxrateService;
		this.stateprovinceService = stateprovinceService;
	}
	
	@GetMapping("/admin/country/")
    public String indexCountry(Model model) {
		model.addAttribute("countries", countryregionService.findAll());
        return "admin/indexCountry";
    }
	
	@GetMapping("/admin/country/add")
	public String addCountry(Model model) {
		model.addAttribute("countryregion",new Countryregion());
		return "admin/addCountry";
	}
	
	@PostMapping("/admin/country/add")
	public String saveCountry(@Validated(BasicInfo.class) @ModelAttribute Countryregion countryregion,BindingResult bindingResult,Model model,@RequestParam(value ="action",required = true) String action) {
		
		if (action.equals("Cancelar")) {
			return "redirect:/admin/country/";
		}
		
		if(bindingResult.hasErrors()) {
			
			model.addAttribute("countries", countryregionService.findAll());
	        return "admin/addCountry";
		}
		if (!action.equalsIgnoreCase("cancel")) {
			System.out.println("Entre");
			countryregionService.save(countryregion);
			
		}
		
		
		return "redirect:/admin/country/";
	}
	
	
	@GetMapping("/admin/country/edit/{id}")
	public String showUpdateCountry(@PathVariable("id") Integer id,Model model) {
		Countryregion countryregion = countryregionService.getCountryRegion(id);
		
		if (countryregion == null)
			throw new IllegalArgumentException("Invalid country Id:" + id);
			
		model.addAttribute("countryregion", countryregion);
		return "admin/updateCountry";
	}
	
	@PostMapping("/admin/country/edit/{id}")
	public String updateCountry(@Validated(BasicInfo.class) @ModelAttribute Countryregion countryregion, BindingResult bindingResult, Model model,@PathVariable("id") Integer id, @RequestParam(value = "action", required = true) String action) {
		
		if (action.equalsIgnoreCase("Cancelar") || action.equalsIgnoreCase("Cancel")) {
			return "redirect:/admin/country/";
		}
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("countryregion", countryregion);
			countryregion.setCountryregionid(id);
			model.addAttribute("countries", countryregionService.findAll());
			return "admin/updateCountry";
		}
		if (!action.equalsIgnoreCase("Cancelar") || !action.equalsIgnoreCase("Cancel")) {
			countryregion.setCountryregionid(id);
			countryregionService.update(countryregion);
			model.addAttribute("countries", countryregionService.findAll());
		}
		return "redirect:/admin/country/";
	}
	
	//SalesTaxrate
	@GetMapping("/admin/sales/")
    public String indexSales(Model model) {
		model.addAttribute("salesses", salestaxrateService.findAll());
        return "admin/indexSales";
    }
	
	@GetMapping("/admin/sales/add")
	public String addSales(Model model) {
		model.addAttribute("salestaxrate",new Salestaxrate());
		model.addAttribute("provinces", stateprovinceService.findAll());
		return "admin/addSales";
	}
	
	@PostMapping("/admin/sales/add")
	public String saveSales(@Validated(BasicInfo.class) @ModelAttribute Salestaxrate salestaxrate,BindingResult bindingResult,Model model,@RequestParam(value ="action",required = true) String action) {
		
		if (action.equalsIgnoreCase("Cancelar") || action.equalsIgnoreCase("Cancel")) {
			return "redirect:/admin/sales/";
		}
		
		if(bindingResult.hasErrors()) {		
			model.addAttribute("salesses", salestaxrateService.findAll());
			model.addAttribute("provinces", stateprovinceService.findAll());
	        return "admin/addSales";
		}
		
		if (!action.equalsIgnoreCase("Cancelar") || !action.equalsIgnoreCase("Cancel")) {
			
			salestaxrateService.save(salestaxrate, salestaxrate.getStateprovince().getStateprovinceid());
		}
		return "redirect:/admin/sales/";
	}
	
	
	@GetMapping("/admin/sales/edit/{id}")
	public String showUpdateSales(@PathVariable("id") Integer id,Model model) {
		Salestaxrate salestaxrate = salestaxrateService.getSalestaxrate(id);
		if (salestaxrate == null)
			throw new IllegalArgumentException("Invalid sales Id:" + id);
		
		model.addAttribute("salestaxrate", salestaxrate);
		model.addAttribute("provinces", stateprovinceService.findAll());
		return "admin/updateSales";
	}
	
	@PostMapping("/admin/sales/edit/{id}")
	public String updateSales(@PathVariable("id") Integer id, @RequestParam(value = "action", required = true) String action, @Validated(BasicInfo.class) @ModelAttribute Salestaxrate salestaxrate, BindingResult bindingResult, Model model) {

		if (action.equalsIgnoreCase("Cancelar") || action.equalsIgnoreCase("Cancel") ) {
			return "redirect:/admin/sales/";
		}
		
		if(bindingResult.hasErrors()) {
			
			model.addAttribute("salestaxrate", salestaxrate);
			salestaxrate.setSalestaxrateid(id);
			model.addAttribute("salesses", salestaxrateService.findAll());
			model.addAttribute("provinces", stateprovinceService.findAll());
			return "admin/updateSales";
		}
		if (!action.equalsIgnoreCase("Cancel") || !action.equalsIgnoreCase("Cancelar")) {
			salestaxrate.setSalestaxrateid(id);
			salestaxrateService.update(salestaxrate,salestaxrate.getStateprovince().getStateprovinceid());
			model.addAttribute("salesses", salestaxrateService.findAll());
			
		}
		return "redirect:/admin/sales/";
	}
	
	
	
}
