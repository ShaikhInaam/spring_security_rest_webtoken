package com.springsecurity.springsecurity.restcontrollers;



import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class MyController {

	
	 @RequestMapping(value = "/hi", method=RequestMethod.GET)
		public String create() {
		   	
	    
			
	    	return "hello";
		}
}
