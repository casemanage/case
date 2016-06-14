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
	public List<AssociateType> getAssociateType() {
		// TODO Auto-generated method stub
		return associateTypeMapper.getAssociateType();
	}

}
