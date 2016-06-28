package com.security.manage.service;

import java.util.List;

import com.security.manage.model.Area;

public interface AreaService {

	List<Area> getAreaAllList(Area area);

	List<Area> getAreaList(Area area);

	int getTotalCount(Area area);

	List<Area> getAreaListByParentId(Area area);

	int getTotalCountByParentId(Area area);

	void deleteAreaById(Area area);

	Area getAreaById(Integer areaId);

	List<Area> getAreaListByName(Area area);

	void updateArea(Area la);

	void saveOrUpdateArea(Area area);

	
}
