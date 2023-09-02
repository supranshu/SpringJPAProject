package com.apitask.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.apitask.model.Customer;
import com.apitask.model.LoginRequest;
import com.apitask.repositories.CustomerRepo;
import com.apitask.repositories.LoginRequestRepository;





@Controller
public class HomeController {

	public String token;
	
	@Autowired
	private CustomerRepo customerRepo;
	
	
	@Autowired
	private LoginRequestRepository loginRequestRepository;
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@PostMapping("/login")
	public String authenticateUser(Model model,String loginid, String password, LoginRequest loginRequest) {
		LoginRequest user = loginRequestRepository.findByLoginId(loginid);
		if (loginRequest != null && user.getPassword().equals(password)) {
	        
			String bearerToken="token1234";
	        
	        token=bearerToken;
	        
	        return "redirect:/dashboard";
		} else {
	        
	        model.addAttribute("error", "Authentication failed");
	        return "home"; 
		}
	}
	@GetMapping("/dashboard")
	public String dashboard(Model model) {
		List<Customer> customers = customerRepo.findAll();
        model.addAttribute("customers", customers);
		return "dashboard";
	}
	
	@PostMapping("/savecustomer")
    public String saveCustomer(@ModelAttribute Customer customer) {
        
        customerRepo.save(customer);    
        return "redirect:/dashboard";
    }
	@GetMapping("/newcustomer")
    public String showNewCustomerForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "newcustomer"; 
    }
	@GetMapping("/editcustomer/{id}")
    public String showEditCustomerForm(@PathVariable Long id, Model model) {
        Customer customer = customerRepo.findById(id).orElse(null);
        if (customer == null) {
            
            return "redirect:/dashboard";
        }
        model.addAttribute("customer", customer);
        return "editcustomer"; 
    }
	@PostMapping("/updatecustomer")
    public String updateCustomer(@ModelAttribute Customer customer) {
		
	    Optional<Customer> optionalCustomer = Optional.of(customerRepo.findByFirstName(customer.getFirstName()));

	    if (optionalCustomer.isPresent()) {
	        
	        Customer existingCustomer = optionalCustomer.get();

	        
	        existingCustomer.setFirstName(customer .getFirstName());
	        existingCustomer.setLastName(customer.getLastName());
	        existingCustomer.setAddress(customer.getAddress());
	        existingCustomer.setCity(customer.getCity());
	        existingCustomer.setState(customer.getState());
	        existingCustomer.setEmail(customer.getEmail());
	        existingCustomer.setPhone(customer.getPhone());

	        
	        customerRepo.save(existingCustomer);

	        return "redirect:/dashboard";
	    } else {
	        
	        return "redirect:/error";
	    }
         
    }
	@GetMapping("/deletecustomer/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        
        customerRepo.deleteById(id);
        return "redirect:/dashboard"; 
    }

	
}
