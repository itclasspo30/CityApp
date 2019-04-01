package com.example.cityspringboot.controller;

import com.example.cityspringboot.bean.Rest;
import com.example.cityspringboot.service.IMainService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RestController {  
    

    @Autowired
    IMainService<Rest> restService;
    
    
    @RequestMapping("/showRest")
    public String findRest(Model model) {
        model.addAttribute("restList", restService.findAll());
        return "showRest";
    }
    
    
    
    @RequestMapping(value = { "/addRest" }, method = RequestMethod.GET)
    public String showAddRestPage(Model model) {
 
        Rest rest = new Rest();
        model.addAttribute("rest", rest);
 
        return "addRest";
    }
    
    
    
    @RequestMapping(value = { "/addRest" }, method = RequestMethod.POST)
    public String saveRest(Model model, @ModelAttribute("rest") Rest rest) {
 
    	String personName = rest.getPersonName();
        int tripID = rest.getTripID();
 
        if (personName != null && personName.length() > 0 && tripID >0) {
            restService.addNew(new Rest(personName, tripID));
        }
        return "redirect:/showRest";
    }
    
    
    
    @RequestMapping(value = {"/delRest"}, method = RequestMethod.GET)
    public String showDelRestPage(Model model) {
    	
    	Rest rest = new Rest();
    	model.addAttribute("rest", rest);
    	
    	return "delRest";
    }
    
    
    
    @RequestMapping(value = {"/delRest"}, method = RequestMethod.POST)
    public String delRest(Model model, @ModelAttribute("rest") Rest rest) {
    	
    	long id = rest.getId();
    	restService.deleteById(id);
    	
    	return "redirect:/showRest";
    }
    
    
    
    @RequestMapping(value = { "/updateRest" }, method = RequestMethod.GET)
    public String showUpdateRestPage(Model model) {
 
        Rest rest = new Rest();
        model.addAttribute("rest", rest);
 
        return "updateRest";
    }
    
    
    
    @RequestMapping(value = { "/updateRest" }, method = RequestMethod.POST)
    public String updateRest(Model model, @ModelAttribute("rest") Rest rest) {
 
    	long id = rest.getId();
    	String personName = rest.getPersonName();
        int tripID = rest.getTripID();
 
        if (personName != null && personName.length() > 0 && tripID >0 &&id >0) {
            restService.updateById(rest);
        }
        return "redirect:/showRest";
    
    }
    
}

