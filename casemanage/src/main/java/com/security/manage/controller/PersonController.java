package com.security.manage.controller;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.security.manage.service.PersonService;
 
@Scope("prototype")
@Controller
@RequestMapping("/person")
public class PersonController {
	@Resource(name = "personService")
	private PersonService personService;
}
