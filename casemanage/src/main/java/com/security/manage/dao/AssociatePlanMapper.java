package com.security.manage.dao;

import com.security.manage.model.AssociatePlan;

public interface AssociatePlanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AssociatePlan record);

    int insertSelective(AssociatePlan record);

    AssociatePlan selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AssociatePlan record);

    int updateByPrimaryKey(AssociatePlan record);
}