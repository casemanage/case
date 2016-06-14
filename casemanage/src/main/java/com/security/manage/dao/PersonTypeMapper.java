package com.security.manage.dao;

import com.security.manage.model.PersonType;

public interface PersonTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PersonType record);

    int insertSelective(PersonType record);

    PersonType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PersonType record);

    int updateByPrimaryKey(PersonType record);
}