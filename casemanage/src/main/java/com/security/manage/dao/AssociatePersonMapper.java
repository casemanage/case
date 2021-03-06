package com.security.manage.dao;

import java.util.List;

import com.security.manage.model.AssociatePerson;

public interface AssociatePersonMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AssociatePerson record);

    int insertSelective(AssociatePerson record);

    AssociatePerson selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AssociatePerson record);

    int updateByPrimaryKeyWithBLOBs(AssociatePerson record);

    int updateByPrimaryKey(AssociatePerson record);

	List<AssociatePerson> getAssociatePersonListById(AssociatePerson associatePerson);
	
	List<AssociatePerson> getExistAssociatePerson(AssociatePerson associatePerson);

	void updateById(AssociatePerson associatePerson);

	List<AssociatePerson> getAssociateListById(AssociatePerson associatePerson);

	int getTotalCount(AssociatePerson associatePerson);

}
