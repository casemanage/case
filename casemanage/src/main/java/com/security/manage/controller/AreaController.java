package com.security.manage.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.security.manage.common.JsonResult;
import com.security.manage.model.Area;
import com.security.manage.model.Associate;
import com.security.manage.service.AreaService;
import com.security.manage.service.AssociateService;
import com.security.manage.util.Constants;

@Scope("prototype")
@Controller
@RequestMapping("/area")
public class AreaController {
	
	@Resource(name = "areaService")
	private AreaService areaService;
	@Resource(name = "associateService")
	private AssociateService associateService;
	
	@RequestMapping(value = "/areaList.do",method=RequestMethod.GET)
	public String areaList(
			Area area,
			HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{ 
		//判断搜索栏是否为空，不为空则转为utf-8编码
		if(area.getSearchName() != null && area.getSearchName() != ""){
			String searchName = new String(area.getSearchName().getBytes("iso8859-1"), "utf-8");
			area.setSearchName(searchName);
		}
		//通过request绑定对象传到前台
		area.setPageSize(Constants.DEFAULT_PAGE_SIZE);
		if (area.getPageNo() == null)
			area.setPageNo(1);
		if(area.getParentid() == null)
			area.setParentid(0);
		List<Area> areaAllList = new ArrayList<Area>();
		int countTotal = 0;
		try {
			areaAllList = areaService.getAreaAllList(area); 
			countTotal = areaService.getTotalCount(area);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		area.setTotalCount(countTotal);
		request.setAttribute("area", area);
		request.setAttribute("areaAllList", areaAllList);
		return "web/area/areaList";
	}
	@ResponseBody
	@RequestMapping(value = "/jsonLoadAreaTreeList.do")
	public List<Area> getAreaList(
			@RequestParam(value = "pid", required = false) Integer pid,
			HttpServletRequest request, HttpServletResponse response) {
		Area area = new Area();
		if (pid != null){
			area.setParentid(pid);
		}else {
			area.setParentid(new Integer(0));
		}
		List<Area> list = new ArrayList<Area>();
		list = areaService.getAreaList(area);
		for(Area a:list){
			a.setText(a.getName());
			if(a.getChildrenCount() > 0){
				a.setState("closed");
			}else{
				a.setChildren(new ArrayList<Area>());
				a.setState("open");
			}
		}
		return list;
	}
	@ResponseBody
	@RequestMapping(value = "/jsonLoadAreaListById.do")
	public JsonResult<Area> jsonLoadAreaListById(
			@RequestParam(value = "areaId", required = false) Integer id,
			@RequestParam(value = "pageNumber", required = false) String pageNumber,
			HttpServletRequest request, HttpServletResponse response) {
		JsonResult<Area> js = new JsonResult<Area>();
		js.setCode(1);
		js.setMessage("获取子节点失败!");
		Area area = new Area();
		if (pageNumber != null && !pageNumber.endsWith("undefined") && pageNumber != ""){
			area.setPageNo(Integer.valueOf(pageNumber));
		}else{
			area.setPageNo(1);
		}
		area.setPageSize(Constants.DEFAULT_PAGE_SIZE); 
		if (id != null) {
			area.setParentid(id);
		} 
		try{
			List<Area> lc = areaService.getAreaListByParentId(area);
			int totalCount = areaService.getTotalCountByParentId(area);
//			Area ar = new Area();
//			ar = areaService.getAreaById(id);
//			lc.add(ar);
			area.setTotalCount(totalCount);
			request.setAttribute("Area", area);
			js.setObj(area); 
			js.setCode(0);
			js.setList(lc); 
			js.setMessage("获取数据成功!");
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return js;
	}
	@ResponseBody
	@RequestMapping(value = "/jsonDeleteArea.do", method = RequestMethod.POST, produces = { "text/html;charset=UTF-8" })
	public JsonResult<Area> deleteAreaById(
			@RequestParam(value = "areaId", required = false) Integer areaId,
			HttpServletRequest request, HttpServletResponse response) {
		JsonResult<Area> js = new JsonResult<Area>();
		Area temparea = new Area();
		Associate a = new Associate();
		js.setCode(new Integer(1));
		js.setMessage("删除失败!");
		try {
			if(areaId != null){
				temparea.setParentid(areaId);
				a.setAreaId(areaId);
			}
			List<Area> ar = areaService.getAreaList(temparea);
			List<Associate> de = associateService.getAreaListByAreaId(a);
			if(ar.size()>0){
				js.setMessage("该区域存在子节点，不能删除!");
				return js;
			}else if(de.size()>0){
				js.setMessage("该区域存在关联机构，不能删除!");
				return js;
			}else{
				Area area = new Area();
				area.setId(areaId);
				area.setFlag(1);  //删除成功，标志位置1
				areaService.deleteAreaById(area);
				js.setCode(new Integer(0));
				js.setMessage("删除成功!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return js;
	}
	@RequestMapping(value = "/areaInfo.do",method=RequestMethod.GET)
	public String areaInfo(
			@RequestParam(value = "areaId", required = false) Integer areaId,
			HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		Area a = new Area();
		try {
			if(areaId != 0){
				a = areaService.getAreaById(areaId);
			}else{
				a.setId(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("area", a);
		return "web/area/areaInfo";
	}
	
	@ResponseBody
	@RequestMapping(value = "/jsonSaveOrUpdateArea.do", method = RequestMethod.POST, produces = { "text/html;charset=UTF-8" })
	public JsonResult<Area> SaveOrUpdateArea(Area area,
			HttpServletRequest request, HttpServletResponse response) {
		JsonResult<Area> js = new JsonResult<Area>();
		js.setCode(new Integer(1));
		js.setMessage("保存失败!");
		Area p = new Area();
		Area old = new Area();
		Area newPArea = new Area();
		Area oldPArea = new Area();
		try {
			if (area.getId() == null || area.getId() == 0)
			{
				area.setId(0);	
			}
			if(area.getId() > 0)
			{
				old = areaService.getAreaById(area.getId());
				if(old.getIsleaf() == 1  && old.getParentid() != area.getParentid())
				{
					js.setMessage("该区域存在子节点，不可迁移!");
					return js;
				}
			}
			if (area.getName() != null) {
				int level = 1;				
				String name = area.getName();				
				p.setName(name);
				p.setId(area.getId());
				List<Area> lc = areaService.getAreaListByName(p);
				if (lc.size() == 0) {
					if(area.getParentid() != null && area.getParentid() != 0)					
					{
						int parentId = area.getParentid();
						newPArea = areaService.getAreaById(parentId);					
						if(old.getParentid() != null && old.getParentid() != parentId && old.getParentid() != 0)
						{
							oldPArea = areaService.getAreaById(old.getParentid());
							int lp = areaService.getTotalCountByParentId(old);
							if(lp == 1)
							{
								oldPArea.setIsleaf(0);
								areaService.updateArea(oldPArea); 
							}
						}
						if(newPArea.getId() != null)
						{							
							level += newPArea.getLevel();
							if(newPArea.getIsleaf() != 1)
							{
								newPArea.setIsleaf(1);
								areaService.updateArea(newPArea); 
							}									
						}
					}else{
						area.setParentid(0);
					}
					area.setFlag(0);
					area.setLevel(level);
					area.setIsleaf(0);
					areaService.saveOrUpdateArea(area); 
					js.setCode(0);											
					js.setMessage("保存成功!");
				}else
				{
					js.setMessage("该区域已存在!");
				}									
			} 
		} catch (Exception ex){
			ex.printStackTrace();
		}
		return js;
	}
}
