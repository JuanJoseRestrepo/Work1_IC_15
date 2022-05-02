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
import com.icesi.taller1.model.Stateprovince;
import com.icesi.taller1.service.AddressService;
import com.icesi.taller1.service.CountryregionService;
import com.icesi.taller1.service.StateprovinceService;

@Controller
public class UserController {
	
	private StateprovinceService stateprovinceService;
	private CountryregionService countryregionService;
	private AddressService addressService;
	
	@Autowired
	public UserController(StateprovinceService stateprovinceService, AddressService addressService,CountryregionService countryregionService) {
		this.stateprovinceService = stateprovinceService;
		this.addressService = addressService;
		this.countryregionService = countryregionService;
	}
	
	@GetMapping("/user/province/")
    public String indexProvinces(Model model) {
		model.addAttribute("stateprovince", stateprovinceService.findAll());
        return "user/indexProvince";
    }
	
	@GetMapping("/user/province/add")
	public String addProvince(Model model) {
		model.addAttribute("stateprovince",new Stateprovince());
		model.addAttribute("countries", countryregionService.findAll());
		return "user/addProvince";
	}
	
	@PostMapping("/user/province/add")
	public String saveProvince(@Validated(BasicInfo.class) @ModelAttribute Stateprovince stateprovince,BindingResult bindingResult,Model model,@RequestParam(value ="action",required = true) String action) {
		
		if(action.equals("Cancelar")) {
			model.addAttribute("stateprovince", stateprovinceService.findAll());
			return "redirect:/user/province/";
		}
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("countries", countryregionService.findAll());
	        return "user/addProvince";
		}
		
		if (!action.equals("Cancelar")) {
			stateprovinceService.save(stateprovince, stateprovince.getCountryregion().getCountryregionid());
		}
		return "redirect:/user/province/";
	}
	
	
	@GetMapping("/user/province/edit/{id}")
	public String showUpdateProvince(@PathVariable("id") Integer id,Model model) {
		Stateprovince stateprovince = stateprovinceService.getStateProvince(id);
		if (stateprovince == null)
			throw new IllegalArgumentException("Invalid country Id:" + id);
		
		model.addAttribute("stateprovince", stateprovince);
		model.addAttribute("countries", countryregionService.findAll());
		return "user/updateProvince";
	}
	
	@PostMapping("/user/province/edit/{id}")
	public String updateProvince(@PathVariable("id") Integer id, @RequestParam(value = "action", required = true) String action, @Validated(BasicInfo.class) @ModelAttribute Stateprovince stateprovince, BindingResult bindingResult, Model model) {
		
		if(action.equals("Cancel")) {
			return "redirect:/user/province/";
		}
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("stateprovince", stateprovinceService.findAll());
			
			return "user/indexProvince";
		}
		if (action != null && !action.equals("Cancel")) {
			stateprovince.setStateprovinceid(id);
			stateprovinceService.update(stateprovince,stateprovince.getCountryregion().getCountryregionid());
			model.addAttribute("provinces", stateprovinceService.findAll());
		}
		return "redirect:/user/province/";
	}
	
	//Direcciones
	@GetMapping("/user/address/")
	public String indexAddress(Model model) {
		model.addAttribute("addresses", addressService.findAll());
		return "user/indexAddress";
	}
	
	@GetMapping("/user/address/add")
	public String addAddress(Model model) {
		model.addAttribute("address",new Address());
		model.addAttribute("provinces", stateprovinceService.findAll());
		return "user/addAddress";
	}
	
	@PostMapping("/user/address/add")
	public String saveCountry(@Validated(BasicInfo.class) @ModelAttribute Address address,BindingResult bindingResult,Model model,@RequestParam(value ="action",required = true) String action) {
		
		if(action.equals("Cancelar")) {
			return "redirect:/user/address/";
		}
		
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("addresses", addressService.findAll());
			model.addAttribute("provinces", stateprovinceService.findAll());
	        return "user/addAddress";
		}
		
		if (!action.equals("Cancelar")) {
			
			addressService.save(address, address.getStateprovince().getStateprovinceid());
		}
		return "redirect:/user/address/";
	}
	
	@GetMapping("/user/address/edit/{id}")
	public String showUpdateAddress(@PathVariable("id") Integer id,Model model) {
		Address address = addressService.getAddress(id);
		if (address == null)
			throw new IllegalArgumentException("Invalid country Id:" + id);
		
		model.addAttribute("address", address);
		model.addAttribute("provinces", stateprovinceService.findAll());
		return "user/updateAddress";
	}
	
	@PostMapping("/user/address/edit/{id}")
	public String updateAddress(@PathVariable("id") Integer id, @RequestParam(value = "action", required = true) String action,@Validated(BasicInfo.class) @ModelAttribute Address address, BindingResult bindingResult, Model model) {
		
		if(action.equals("Cancel")) {
			return "redirect:/user/address/";
		}
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("addresses", addressService.findAll());
			
			return "user/indexAddress";
		}
		if (action != null && !action.equals("Cancel")) {
			address.setAddressid(id);
			addressService.update(address,address.getStateprovince().getStateprovinceid());
			model.addAttribute("provinces", stateprovinceService.findAll());
		}
		return "redirect:/user/address/";
	}

}
