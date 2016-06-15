package com.security.manage.dao;
 
 
import java.util.List;

import com.security.manage.model.PersonCar;

public interface PersonCarMapper {

	int deleteByPrimaryKey(Integer id);

    int insert(PersonCar record);

    int insertSelective(PersonCar record);

    PersonCar selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PersonCar record); 

    int updateByPrimaryKey(PersonCar record);

	List<PersonCar> getExistPersonCar(PersonCar p);

	List<PersonCar> getPersonCarList(PersonCar personCar);

	int getPersonCarTotalCount(PersonCar personCar);

}
