package com.security.manage.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
 
import com.security.manage.dao.PersonTypeMapper; 
import com.security.manage.dao.PersonLevelMapper;
import com.security.manage.dao.PersonMapper;
import com.security.manage.model.Person;
import com.security.manage.model.PersonLevel; 
import com.security.manage.model.PersonType;
import com.security.manage.service.PersonService;

@Service("personService")
public class PersonServiceImpl implements PersonService {
	
	@Resource 
	private PersonTypeMapper personTypeMapper;
	
	@Resource 
	private PersonMapper personMapper;
	
	@Resource
	private PersonLevelMapper personLevelMapper;
	@Override
	public List<Person> getPersonList(Person person) {
		return personMapper.getPersonList(person);
	}

	@Override
	public int getTotal(Person person) {
		return personMapper.getTotal(person);
	}

	@Override
	public Person getPersonById(Integer personId) {
		// TODO Auto-generated method stub
		return personMapper.selectByPrimaryKey(personId);
	}

	@Override
	public List<PersonType> getPersonType() {
		// TODO Auto-generated method stub
		return personTypeMapper.getPersonType();
	}

	@Override
	public List<PersonLevel> getPersonLevel() {
		// TODO Auto-generated method stub
		return personLevelMapper.getPersonLevel();
	}

	@Override
	public List<Person> getExistPersonList(Person p) {
		// TODO Auto-generated method stub
		return personMapper.getExistPersonList(p);
	}

	@Override
	public void saveOrUpdatePerson(Person person) {
		// TODO Auto-generated method stub
		if(person.getId() > 0){
			personMapper.updateByPrimaryKeySelective(person);
		}else{
			personMapper.insert(person);
		} 
	}

	@Override
	public List<PersonType> getPersonTypeList(PersonType personType)
	{
		return personTypeMapper.getPersonTypeList(personType);
	}
	
	@Override
	public PersonType getPersonTypeById(Integer personTypeId)
	{
		return personTypeMapper.selectByPrimaryKey(personTypeId);
	}
	
	@Override
	public int getPersonTypeTotalCount(PersonType personType)
	{
		return personTypeMapper.getPersonTypeTotalCount(personType); 
	}
	@Override
	public List<PersonType> getExistPersonType(PersonType personType) {
		// TODO Auto-generated method stub
		return personTypeMapper.getExistPersonType(personType);
	}

	@Override
	public void saveOrUpdatePersonType(PersonType personType) {
		// TODO Auto-generated method stub
		if(personType.getId() > 0 )
		{
			personTypeMapper.updateByPrimaryKeySelective(personType);
		}else
		{
			personTypeMapper.insert(personType);
		}
	}

	
}
