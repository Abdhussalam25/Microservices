package com.salambasha.rest.webservices.HelloWorldController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/salam")
public class HelloWorldRestController {
	
	@GetMapping("/")
	public String retrunString() {
		
		return "Assalaamu alaikkum";
	}
	
	@GetMapping("newbean")
	public BeanExample returnBean() {
		
		return new BeanExample("Alhamdhulillaah new bean reflected on api");
	}
	
	@GetMapping("/pathvariable/{name}")
	public BeanExample returnBeanWithPathVariable(@PathVariable String name) {
		
		return new BeanExample(String.format("Alhamdhulillaah new bean reflected on api with pathvariable %s", name));
	}

	

}
