package com.salambasha.rest.webservices.Helloword;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@GetMapping("/greetings")
//RequestMapping( method = RequestMethod.GET, path = "/greetings")
	public String sayGreetings() {
		
		return "Assalaamu alaikkum rahmathullaahi barakkaathuhu";
	}
	
	@GetMapping("/bean")
	//RequestMapping( method = RequestMethod.GET, path = "/greetings")
		public HelloWorldBean retrunHelloWorldBean() {
			
			return  new HelloWorldBean("Assalaamu alaikkum Rahmathullaahi barakkaathuhu");
		}
	
	@GetMapping("/bean/path-variable/{name}")
	//RequestMapping( method = RequestMethod.GET, path = "/greetings")
		public HelloWorldBean HelloWorldWithPathVariable( @PathVariable String name) {
			
			return  new HelloWorldBean(String.format("Assalaamu alaikkum Rahmathullaahi barakkaathuhu , %s",name));
		}
	
	
}
