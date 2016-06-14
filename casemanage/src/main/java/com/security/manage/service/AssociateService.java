package com.security.manage.service;

import java.util.List;

import com.security.manage.model.Associate;
import com.security.manage.model.AssociateType;

public interface AssociateService {

	List<Associate> getAssociateList(Associate associate);

	int getTotalCount(Associate associate);

	Associate getAssociateById(Integer id);

	List<AssociateType> getAssociateType();

}
