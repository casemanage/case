package com.security.manage.dao;

import java.util.List;

import com.security.manage.model.Associate;
import com.security.manage.model.AssociatePerson;

public interface AssociatePersonMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AssociatePerson record);

    int insertSelective(AssociatePerson record);

    AssociatePerson selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AssociatePerson record);

    int updateByPrimaryKeyWithBLOBs(AssociatePerson record);

    int updateByPrimaryKey(AssociatePerson record);

	List<AssociatePerson> getAssociatePersonListById(Integer associateId);

	List<Associate> getExistAssociate(AssociatePerson a);

	void updateById(AssociatePerson associatePerson);
}