package com.security.manage.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.security.manage.dao.AreaMapper;
import com.security.manage.model.Area;
import com.security.manage.service.AreaService;

@Service("areaService")
public class AreaServiceImpl implements AreaService{
	
	@Resource
	private AreaMapper areaMapper;
	@Override
	public List<Area> getAreaAllList(Area area) {
		// TODO Auto-generated method stub
		return areaMapper.getAreaAllList(area);
	}

	@Override
	public List<Area> getAreaList(Area area) {
		// TODO Auto-generated method stub
		return areaMapper.getAreaList(area);
	}

	@Override
	public int getTotalCount(Area area) {
		// TODO Auto-generated method stub
		return areaMapper.getTotalCount(area);
	}

	@Override
	public List<Area> getAreaListByParentId(Area area) {
		// TODO Auto-generated method stub
		return areaMapper.getAreaListByParentId(area);
	}

	@Override
	public int getTotalCountByParentId(Area area) {
		// TODO Auto-generated method stub
		return areaMapper.getTotalCountByParentId(area);
	}

	@Override
	public void deleteAreaById(Area area) {
		// TODO Auto-generated method stub
		areaMapper.updateByPrimaryKeySelective(area);
	}

	@Override
	public Area getAreaById(Integer areaId) {
		// TODO Auto-generated method stub
		Area area = areaMapper.selectByPrimaryKey(areaId);
		return area;
	}

	@Override
	public List<Area> getAreaListByName(Area area) {
		// TODO Auto-generated method stub
		return areaMapper.getAreaListByName(area);
	}

	@Override
	public void updateArea(Area area) {
		// TODO Auto-generated method stub
		areaMapper.updateByPrimaryKeySelective(area);
	}

	@Override
	public void saveOrUpdateArea(Area area) {
		if(area.getId() > 0)
		{
			areaMapper.updateByPrimaryKey(area);
		}else
			areaMapper.insert(area);
	}

}
