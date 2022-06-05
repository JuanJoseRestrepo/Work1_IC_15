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
	
	private DelegatedUser du;
	
	@Autowired
	public UserController(DelegatedUser du) {
		this.du = du;
	}
	
	@GetMapping("/user/province/")
    public String indexProvinces(Model model) {
		model.addAttribute("stateprovince", du.getAllStateprovinces());
        return "user/indexProvince";
    }
	
	@GetMapping("/user/province/add")
	public String addProvince(Model model) {
		model.addAttribute("stateprovince",new Stateprovince());
		model.addAttribute("countries", du.getAllCountryregion());
		return "user/addProvince";
	}
	
	@PostMapping("/user/province/add")
	public String saveProvince(@Validated(BasicInfo.class) @ModelAttribute Stateprovince stateprovince,BindingResult bindingResult,Model model,@RequestParam(value ="action",required = true) String action) {
		
		if(action.equalsIgnoreCase("Cancelar") || action.equalsIgnoreCase("Cancel")) {
			model.addAttribute("stateprovince", du.getAllStateprovinces());
			return "redirect:/user/province/";
		}
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("countries", du.getAllCountryregion());
	        return "user/addProvince";
		}
		
		if (!action.equalsIgnoreCase("Cancelar") || !action.equalsIgnoreCase("Cancel")) {
			du.createStateprovince(stateprovince);
		}
		return "redirect:/user/province/";
	}
	
	
	@GetMapping("/user/province/edit/{id}")
	public String showUpdateProvince(@PathVariable("id") Integer id,Model model) {
		Stateprovince stateprovince = du.getStateprovince(id);
		if (stateprovince == null)
			throw new IllegalArgumentException("Invalid country Id:" + id);
		
		model.addAttribute("stateprovince", stateprovince);
		model.addAttribute("countries", du.getAllCountryregion());
		return "user/updateProvince";
	}
	
	@PostMapping("/user/province/edit/{id}")
	public String updateProvince(@PathVariable("id") Integer id, @RequestParam(value = "action", required = true) String action, @Validated(BasicInfo.class) @ModelAttribute Stateprovince stateprovince, BindingResult bindingResult, Model model) {

		if(action.equalsIgnoreCase("Cancelar") || action.equalsIgnoreCase("Cancel")) {
			System.out.println("Entre aca hola 1");
			return "redirect:/user/province/";
		}
		
		if(bindingResult.hasErrors()) {
			stateprovince.setStateprovinceid(id);
			model.addAttribute("stateprovince", stateprovince);
			model.addAttribute("countries",du.getAllCountryregion());
			return "user/updateProvince";
		}
		if (!action.equalsIgnoreCase("Cancelar") || !action.equalsIgnoreCase("Cancel")) {
			stateprovince.setStateprovinceid(id);
			du.updateStateprovince(stateprovince.getCountryregion().getCountryregionid(), stateprovince);
			model.addAttribute("provinces", du.getAllStateprovinces());
		}
		return "redirect:/user/province/";
	}
	
	//Direcciones
	@GetMapping("/user/address/")
	public String indexAddress(Model model) {
		model.addAttribute("addresses", du.getAllAddresses());
		return "user/indexAddress";
	}
	
	@GetMapping("/user/address/add")
	public String addAddress(Model model) {
		model.addAttribute("address",new Address());
		model.addAttribute("provinces", du.getAllStateprovinces());
		return "user/addAddress";
	}
	
	@PostMapping("/user/address/add")
	public String saveCountry(@Validated(BasicInfo.class) @ModelAttribute Address address,BindingResult bindingResult,Model model,@RequestParam(value ="action",required = true) String action) {
		
		if(action.equalsIgnoreCase("Cancelar") || action.equalsIgnoreCase("Cancel")) {
			return "redirect:/user/address/";
		}
		
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("addresses",du.getAllAddresses());
			model.addAttribute("provinces", du.getAllStateprovinces());
	        return "user/addAddress";
		}
		
		if (!action.equalsIgnoreCase("Cancelar") || !action.equalsIgnoreCase("Cancel")) {
			du.createAddress(address);
		}
		return "redirect:/user/address/";
	}
	
	@GetMapping("/user/address/edit/{id}")
	public String showUpdateAddress(@PathVariable("id") Integer id,Model model) {
		Address address = du.getAddress(id);
		if (address == null)
			throw new IllegalArgumentException("Invalid country Id:" + id);
		
		model.addAttribute("address", address);
		model.addAttribute("provinces", du.getAllStateprovinces());
		return "user/updateAddress";
	}
	
	@PostMapping("/user/address/edit/{id}")
	public String updateAddress(@PathVariable("id") Integer id, @RequestParam(value = "action", required = true) String action,@Validated(BasicInfo.class) @ModelAttribute Address address, BindingResult bindingResult, Model model) {
		
		if(action.equalsIgnoreCase("Cancelar") || action.equalsIgnoreCase("Cancel")) {
			return "redirect:/user/address/";
		}
		
		if(bindingResult.hasErrors()) {
			
			model.addAttribute("address", address);
			address.setAddressid(id);
			model.addAttribute("addresses", du.getAllAddresses());
			model.addAttribute("provinces", du.getAllStateprovinces());
			return "user/updateAddress";
		}
		if (!action.equalsIgnoreCase("Cancelar") || !action.equalsIgnoreCase("Cancel")) {
			address.setAddressid(id);
			du.updateAddress(address.getStateprovince().getStateprovinceid(), address);
			model.addAttribute("provinces", du.getAllStateprovinces());
		}
		return "redirect:/user/address/";
	}

}
