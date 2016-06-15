package com.security.manage.dao;

import java.util.List;

import com.security.manage.model.Person;
import com.security.manage.model.TypeStatistic;

public interface PersonMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Person record);

    int insertSelective(Person record);

    Person selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Person record);

    int updateByPrimaryKeyWithBLOBs(Person record);

    int updateByPrimaryKey(Person record);

	List<Person> getPersonList(Person person);

	int getTotal(Person person);

	List<Person> getExistPersonList(Person p);

	List<TypeStatistic> getPersonTypeCountList();

	List<TypeStatistic> getPersonStationCountList();
}