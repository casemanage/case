package com.security.manage.service;

import java.util.List;

import com.security.manage.model.Area;
import com.security.manage.model.Associate;

public interface AreaService {

	List<Area> getAreaAllList(Area area);

	List<Area> getAreaList(Area area);

	int getTotalCount(Area area);

	List<Area> getAreaListByParentId(Area area);

	int getTotalCountByParentId(Area area);

	void deleteAreaById(Area area);

	Area getAreaById(Integer areaId);
	
}
