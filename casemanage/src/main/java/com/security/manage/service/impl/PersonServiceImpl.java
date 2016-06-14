package com.security.manage.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.security.manage.dao.PersonCarMapper;
import com.security.manage.dao.PersonTypeMapper; 
import com.security.manage.dao.PersonLevelMapper;
import com.security.manage.dao.PersonMapper;
import com.security.manage.model.Person;
import com.security.manage.model.PersonCar;
import com.security.manage.model.PersonLevel; 
import com.security.manage.model.PersonType;
import com.security.manage.service.PersonService;

@Service("personService")
public class PersonServiceImpl implements PersonService {
	
	@Resource 
	private PersonTypeMapper personTypeMapper;
	
	@Resource 
	private PersonCarMapper personCarMapper;
	
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
	public List<PersonLevel> getPersonLevelList(PersonLevel personLevel) 
	{
		return personTypeMapper.getPersonLevelList(personLevel);
	}

	
	@Override
	public PersonType getPersonTypeById(Integer personTypeId)
	{
		return personTypeMapper.selectByPrimaryKey(personTypeId);
	}
	
	@Override
	public PersonLevel getPersonLevelById(Integer personLevelId) {
		// TODO Auto-generated method stub
		return personLevelMapper.selectByPrimaryKey(personLevelId);
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

	@Override
	public int getPersonLevelTotalCount(PersonLevel personLevel) {
		// TODO Auto-generated method stub
		return personLevelMapper.getPersonLevelTotalCount(personLevel);
	}

	@Override
	public List<PersonLevel> getExistPersonLevel(PersonLevel personLevel) {
		// TODO Auto-generated method stub
		return personTypeMapper.getExistPersonLevel(personLevel);
	}

	@Override
	public void saveOrUpdatePersonLevel(PersonLevel personLevel) {
		// TODO Auto-generated method stub
		if(personLevel.getId() > 0)
		{
			personLevelMapper.insertSelective(personLevel);
		}
		else
		{
			personLevelMapper.insert(personLevel);
		}
		
	}

	@Override
	public PersonCar getPersonCarById(Integer personCarId) {
		// TODO Auto-generated method stub
		return personCarMapper.selectByPrimaryKey(personCarId);
	}

	@Override
	public List<PersonCar> getExistPersonCar(PersonCar p) {
		// TODO Auto-generated method stub
		return personCarMapper.getExistPersonCar(p);
	}

	@Override
	public void saveOrUpdatePersonCar(PersonCar personCar) {
		// TODO Auto-generated method stub
		if(personCar.getId() > 0 )
		{
			personCarMapper.updateByPrimaryKeySelective(personCar);
		}else
		{
			personCarMapper.insert(personCar);
		}
	}

	@Override
	public List<PersonCar> getPersonCarList(PersonCar personCar) {
		// TODO Auto-generated method stub
		return personCarMapper.getPersonCarList(personCar);
	}

	@Override
	public int getPersonCarTotalCount(PersonCar personCar) {
		// TODO Auto-generated method stub
		return personCarMapper.getPersonCarTotalCount(personCar);
	}

}
