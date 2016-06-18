package com.security.manage.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.security.manage.dao.AssociateMapper;
import com.security.manage.dao.AssociatePersonMapper;
import com.security.manage.dao.AssociatePlanMapper;
import com.security.manage.dao.AssociateTypeMapper;
import com.security.manage.model.Associate;
import com.security.manage.model.AssociatePerson;
import com.security.manage.model.AssociatePlan;
import com.security.manage.model.AssociateType;
import com.security.manage.model.TypeStatistic;
import com.security.manage.service.AssociateService; 

@Service("associateService")
public class AssociateServiceImpl implements AssociateService{

	
	@Resource
	private AssociateMapper associateMapper;
	@Resource
	private AssociateTypeMapper associateTypeMapper;
	@Resource
	private AssociatePersonMapper associatePersonMapper;
	@Resource
	private AssociatePlanMapper associatePlanMapper;
	
	@Override
	public List<Associate> getAssociateList(Associate associate) {
		// TODO Auto-generated method stub
		return associateMapper.getAssociateList(associate);
	}

	@Override
	public int getTotalCount(Associate associate) {
		// TODO Auto-generated method stub
		return associateMapper.getTotalCount(associate);
	}

	@Override
	public Associate getAssociateById(Integer id) {
		// TODO Auto-generated method stub
		return associateMapper.selectByPrimaryKey(id);
	}
	
	
	
	@Override
	public List<AssociateType> getAssociateTypeList(AssociateType associateType) {
		// TODO Auto-generated method stub
		return associateTypeMapper.getAssociateTypeList(associateType);
	}

	@Override
	public int getAssociateTypeTotalCount(AssociateType associateType) {
		// TODO Auto-generated method stub
		return associateTypeMapper.getAssociateTypeTotalCount(associateType);
	}
	
	@Override
	public AssociateType getAssociateTypeById(Integer id) {
		// TODO Auto-generated method stub
		return associateTypeMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<AssociateType> getExistAssociateType(AssociateType associateType) {
		// TODO Auto-generated method stub
		return associateTypeMapper.getExistAssociateType(associateType);
	}

	@Override
	public void saveOrUpdateAssociateType(AssociateType associateType) {
		// TODO Auto-generated method stub
		if(associateType.getId() > 0 )
		{
			associateTypeMapper.updateByPrimaryKeySelective(associateType);
		}else
		{			
			associateTypeMapper.insert(associateType);
		}
	}

	@Override 
	public List<TypeStatistic> getAssociateTypeCountList() {
		// TODO Auto-generated method stub
		return associateMapper.getAssociateTypeCountList();
	}

	@Override
	public List<TypeStatistic> getAssociateStationCountList() {
		// TODO Auto-generated method stub
		return associateMapper.getAssociateStationCountList();
	}
	public List<Associate> getExistAssociate(Associate a) {
		// TODO Auto-generated method stub
		return associateMapper.getExistAssociate(a);
	}

	@Override
	public void saveOrUpdateAssociate(Associate associate) {
		// TODO Auto-generated method stub
		if(associate.getId() > 0){
			associateMapper.updateByPrimaryKeySelective(associate);
		}else{
			associateMapper.insert(associate);
		}
	}

	@Override
	public List<AssociatePerson> getAssociateListById(Integer associateId) {
		// TODO Auto-generated method stub
		return associatePersonMapper.getAssociateListById(associateId);
	}

	@Override
	public List<Associate> getExistAssociate(AssociatePerson a) {
		// TODO Auto-generated method stub
		return associatePersonMapper.getExistAssociate(a);
	}

	@Override
	public void updateAssociatePerson(AssociatePerson associatePerson) {
		// TODO Auto-generated method stub
		associatePersonMapper.insert(associatePerson); 
	}

	@Override
	public void deleteMemberById(AssociatePerson AssociatePerson) {
		// TODO Auto-generated method stub
		associatePersonMapper.updateById(AssociatePerson);
	}

	@Override
	public List<AssociatePlan> getAssociatePlanListById(Integer associateId) {
		// TODO Auto-generated method stub
		return associatePlanMapper.getAssociatePlanListById(associateId);
	}

	@Override
	public void saveOrUpdateAssociatePlan(List<String> urlList,
			Integer associateid) {
		// TODO Auto-generated method stub
		for(String url : urlList){
			AssociatePlan ap = new AssociatePlan();
			ap.setId(0);
			ap.setAssociateid(associateid);
			ap.setPlanurl(url);
			associatePlanMapper.insert(ap);
		}
	}

	@Override
	public AssociatePerson getAssociateMemberById(Integer id) {
		// TODO Auto-generated method stub
		return associatePersonMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Associate> getAssociateListByCreatorname(Associate associate) {
		// TODO Auto-generated method stub
		return associateMapper.getAssociateListByCreatorname(associate);
	}

	

}
