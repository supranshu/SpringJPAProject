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
import com.apitask.service.AuthenticationService;
import com.apitask.service.CustomAuthenticationException;



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
	        //String bearerToken = authenticationService.authenticateUser(loginRequest);
			String bearerToken="token1234";
	        // Store the bearer token securely for subsequent API calls
	        token=bearerToken;
	        // Redirect to another page after successful login
	        return "redirect:/dashboard";
		} else {
	        // Handle authentication failure, e.g., show an error message
	        model.addAttribute("error", "Authentication failed");
	        return "home"; // Return to the login page with an error message
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
        // Implement saving customer logic here using the customerService
        customerRepo.save(customer);    
        return "redirect:/dashboard"; // Redirect back to the dashboard page
    }
	@GetMapping("/newcustomer")
    public String showNewCustomerForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "newcustomer"; // Return the name of your new customer HTML file
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
		// 1. Retrieve the existing customer from the database by ID
	    Optional<Customer> optionalCustomer = Optional.of(customerRepo.findByFirstName(customer.getFirstName()));

	    if (optionalCustomer.isPresent()) {
	        // 2. Get the existing customer
	        Customer existingCustomer = optionalCustomer.get();

	        // 3. Update the customer's fields with the new data
	        existingCustomer.setFirstName(customer .getFirstName());
	        existingCustomer.setLastName(customer.getLastName());
	        existingCustomer.setAddress(customer.getAddress());
	        existingCustomer.setCity(customer.getCity());
	        existingCustomer.setState(customer.getState());
	        existingCustomer.setEmail(customer.getEmail());
	        existingCustomer.setPhone(customer.getPhone());

	        // Save the updated customer back to the database
	        customerRepo.save(existingCustomer);

	        // Redirect back to the dashboard page
	        return "redirect:/dashboard";
	    } else {
	        // Handle the case where the customer with the specified ID is not found
	        // You can show an error message or redirect to an error page
	        return "redirect:/error";
	    }
         
    }
	@GetMapping("/deletecustomer/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        
        customerRepo.deleteById(id);
        return "redirect:/dashboard"; 
    }

	
}
