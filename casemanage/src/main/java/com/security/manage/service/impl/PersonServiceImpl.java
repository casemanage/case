package com.security.manage.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.security.manage.dao.PersonTypeMapper;
import com.security.manage.model.PersonType;
import com.security.manage.service.PersonService;

@Service("personService")
public class PersonServiceImpl implements PersonService {
	
	@Resource
	private PersonTypeMapper personTypeMapper;
	
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
			personTypeMapper.insertSelective(personType);
		}else
		{
			personTypeMapper.insert(personType);
		}
	}

	
}
