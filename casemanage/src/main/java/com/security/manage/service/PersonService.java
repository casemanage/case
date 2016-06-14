package com.security.manage.service;

import java.util.List;

import com.security.manage.model.PersonType;

public interface PersonService {

	List<PersonType> getPersonTypeList(PersonType personType);

	int getPersonTypeTotalCount(PersonType personType);

	List<PersonType> getExistPersonType(PersonType personType);

	void saveOrUpdatePersonType(PersonType personType);

	PersonType getPersonTypeById(Integer personTypeId);

}
