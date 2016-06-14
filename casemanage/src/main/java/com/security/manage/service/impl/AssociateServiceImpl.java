package com.security.manage.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.security.manage.dao.AssociateMapper;
import com.security.manage.dao.AssociateTypeMapper;
import com.security.manage.model.Associate;
import com.security.manage.model.AssociateType;
import com.security.manage.service.AssociateService; 

@Service("associateService")
public class AssociateServiceImpl implements AssociateService{

	
	@Resource
	private AssociateMapper associateMapper;
	@Resource
	private AssociateTypeMapper associateTypeMapper;
	
	
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

	

}
