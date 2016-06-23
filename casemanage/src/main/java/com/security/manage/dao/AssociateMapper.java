package com.security.manage.dao;

import java.util.List;

import com.security.manage.model.Associate;
import com.security.manage.model.TypeStatistic;

public interface AssociateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Associate record);

    int insertSelective(Associate record);

    Associate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Associate record);

    int updateByPrimaryKeyWithBLOBs(Associate record);

    int updateByPrimaryKey(Associate record);

	List<Associate> getAssociateList(Associate associate);

	int getTotalCount(Associate associate);
 
	List<TypeStatistic> getAssociateTypeCountList();

	List<TypeStatistic> getAssociateStationCountList(); 
	
	List<Associate> getExistAssociate(Associate a);

	List<Associate> getAssociateListByCreatorname(Associate associate);


}
