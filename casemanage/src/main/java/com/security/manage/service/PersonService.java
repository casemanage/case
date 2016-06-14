package com.security.manage.service;

import java.util.List;

import com.security.manage.model.Person;
import com.security.manage.model.PersonLevel;
import com.security.manage.model.PersonType;

public interface PersonService {

	List<Person> getPersonList(Person person);

	int getTotal(Person person);

	Person getPersonById(Integer personId);

	List<PersonType> getPersonType();

	List<PersonLevel> getPersonLevel();

	List<Person> getExistPersonList(Person p);

	void saveOrUpdatePerson(Person person);

}
