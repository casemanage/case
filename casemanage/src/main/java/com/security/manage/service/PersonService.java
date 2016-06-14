package com.security.manage.service;

import java.util.List;
 

import com.security.manage.model.Person;
import com.security.manage.model.PersonLevel; 
import com.security.manage.model.PersonType;
import com.security.manage.model.TypeStatistic;

public interface PersonService {
 
	List<PersonType> getPersonTypeList(PersonType personType);

	int getPersonTypeTotalCount(PersonType personType);

	List<PersonType> getExistPersonType(PersonType personType);

	void saveOrUpdatePersonType(PersonType personType);

	PersonType getPersonTypeById(Integer personTypeId); 
	
	List<Person> getPersonList(Person person);

	int getTotal(Person person);

	Person getPersonById(Integer personId);

	List<PersonType> getPersonType();

	List<PersonLevel> getPersonLevel();

	List<Person> getExistPersonList(Person p);

	void saveOrUpdatePerson(Person person);

	List<TypeStatistic> getPersonTypeCountList();

	List<TypeStatistic> getPersonStationCountList(); 

}
