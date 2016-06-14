package com.security.manage.dao;

import java.util.List;

import com.security.manage.model.PersonLevel;

public interface PersonLevelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PersonLevel record);

    int insertSelective(PersonLevel record);

    PersonLevel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PersonLevel record);

    int updateByPrimaryKey(PersonLevel record);

	List<PersonLevel> getPersonLevel();

	int getPersonLevelTotalCount(PersonLevel personLevel);

	List<PersonLevel> getPersonLevelList(PersonLevel personLevel);

	List<PersonLevel> getExistPersonLevel(PersonLevel personLevel); 
}