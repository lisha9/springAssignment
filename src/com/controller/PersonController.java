package com.controller;

import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.model.Person;
import com.model.PersonJpa;

@Controller
public class PersonController {

	private ModelMap setPersons(ModelMap map)
	{
		ArrayList<Person> per=new PersonJpa().selectStar();
		map.addAttribute("list",per);
		return map;
	}
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public ModelAndView usetInit(ModelMap map)
	{
		System.out.println("in form");
		setPersons(map);
		return new ModelAndView("addperson","command",new Person());
	}
	
	@RequestMapping(value="/delete/{id}")
	public ModelAndView deletePerson(@PathVariable int id,ModelMap map)
	{
		System.out.println("in del"+id);
		new PersonJpa().delete(id);
		setPersons(map);
		map.addAttribute("onceDeEd",true);
		return new ModelAndView("addperson","command",new Person());
	}
	
	
	@RequestMapping(value="/edit/{id}",method=RequestMethod.GET)
	public ModelAndView editPerson(@PathVariable int id,ModelMap map)
	{
		System.out.println("in edit"+id);
		Person pp=new PersonJpa().getSinglePerson(id);
		map.addAttribute("inEditMode",true);
		map.addAttribute("p",pp);
		setPersons(map);
		return new ModelAndView("addperson","command",new Person());
	}
	
	@RequestMapping(value="/edit/update",method=RequestMethod.POST)
	public ModelAndView updatePerson(@ModelAttribute Person p,ModelMap map)
	{
		System.out.println("in update");
		
		new PersonJpa().edit(p);
		map.addAttribute("onceUpdated",true);
		setPersons(map);
		return new ModelAndView("addperson","command",new Person());
	}
	
	@RequestMapping(value="/persons",method=RequestMethod.POST)
	public ModelAndView view(@ModelAttribute("SpringWeb")Person p,ModelMap model)
	{
		System.out.println("in view");
		new PersonJpa().insert(p);
		setPersons(model);
		return new ModelAndView("addperson","command",new Person());
		
	}
	
}
