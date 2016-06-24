package com.security.manage.dao;

import java.util.List;

import com.security.manage.model.Area;

public interface AreaMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Area record);

    int insertSelective(Area record);

    Area selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Area record);

    int updateByPrimaryKey(Area record);

	List<Area> getAreaAllList(Area area);

	List<Area> getAreaList(Area area);

	int getTotalCount(Area area);

	List<Area> getAreaListByParentId(Area area);

	int getTotalCountByParentId(Area area);
}