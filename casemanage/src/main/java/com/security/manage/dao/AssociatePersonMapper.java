package com.security.manage.dao;

import com.security.manage.model.AssociatePerson;

public interface AssociatePersonMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AssociatePerson record);

    int insertSelective(AssociatePerson record);

    AssociatePerson selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AssociatePerson record);

    int updateByPrimaryKeyWithBLOBs(AssociatePerson record);

    int updateByPrimaryKey(AssociatePerson record);
}