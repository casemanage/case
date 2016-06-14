package com.security.manage.service;

import java.util.List;

import com.security.manage.model.Associate;
import com.security.manage.model.AssociatePerson;
import com.security.manage.model.AssociateType;
import com.security.manage.model.TypeStatistic;

public interface AssociateService {

	List<Associate> getAssociateList(Associate associate);

	int getTotalCount(Associate associate);

	Associate getAssociateById(Integer id);

	List<AssociateType> getAssociateTypeList(AssociateType associateType);

	int getAssociateTypeTotalCount(AssociateType associateType);

	AssociateType getAssociateTypeById(Integer associateTypeId);

	List<AssociateType> getExistAssociateType(AssociateType associateType);

	void saveOrUpdateAssociateType(AssociateType associateType);
 
	List<TypeStatistic> getAssociateTypeCountList();

	List<TypeStatistic> getAssociateStationCountList(); 
	
	List<Associate> getExistAssociate(Associate a);

	void saveOrUpdateAssociate(Associate associate);

	List<AssociatePerson> getAssociateListById(Integer associateId);

	List<Associate> getExistAssociate(AssociatePerson a);

	void updateAssociatePerson(AssociatePerson associatePerson); 




}
