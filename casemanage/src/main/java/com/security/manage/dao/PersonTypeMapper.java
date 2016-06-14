package com.security.manage.dao;

import java.util.List;

import com.security.manage.model.PersonType;

public interface PersonTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PersonType record);

    int insertSelective(PersonType record);

    PersonType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PersonType record);

    int updateByPrimaryKey(PersonType record);

	int getPersonTypeTotalCount(PersonType personType);

	List<PersonType> getPersonTypeList(PersonType personType);

	List<PersonType> getExistPersonType(PersonType personType);

	
}