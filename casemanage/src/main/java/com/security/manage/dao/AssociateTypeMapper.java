package com.security.manage.dao;

import java.util.List;

import com.security.manage.model.AssociateType;

public interface AssociateTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AssociateType record);

    int insertSelective(AssociateType record);

    AssociateType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AssociateType record);

    int updateByPrimaryKey(AssociateType record);	

	List<AssociateType> getAssociateTypeList(AssociateType associateType);

	int getAssociateTypeTotalCount(AssociateType associateType);

	List<AssociateType> getExistAssociateType(AssociateType associateType);
}