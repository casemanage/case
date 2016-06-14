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
import org.springframework.web.bind.annotation.RequestParam;

import com.security.manage.model.Associate;
import com.security.manage.model.AssociateType;
import com.security.manage.service.AssociateService;  
import com.security.manage.util.Constants; 

@Scope("prototype")
@Controller
@RequestMapping("/associate")
public class AssociateController {
	@Resource(name = "associateService")
	private AssociateService associateService;
	
	/**
	 * 社会组织管理
	 * @param associate
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/associateList.do")
	public String associateList(
			Associate associate,
			HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{ 
		//判断搜索栏是否为空，不为空则转为utf-8编码
		if(associate.getSearchName() != null && associate.getSearchName() != ""){
			String searchName = new String(associate.getSearchName().getBytes(
					"iso8859-1"), "utf-8");
			associate.setSearchName(searchName);
		}
		//设置页面初始值及页面尺寸
		//associate.setPageSize(Constants.DEFAULT_PAGE_SIZE); 
		//int pageNo = associate.getPageSize()*(associate.getPageNo()-1);
		//associate.setPageCount(pageNo);
		List<Associate> associatelist = new ArrayList<Associate>();
		int countTotal = 0;
		try { 
			//Associate ax = associateService.getAssociateById(1);
			associatelist = associateService.getAssociateList(associate); 
//			for(Associate a :associatelist){
//				if(a.getTimespan()!= null){
//					String strDate = new String(a.getTimespan(),"UTF-8");
//					try{
//						 int year = Integer.parseInt(strDate.substring(0, 4));
//					     int month = Integer.parseInt(strDate.substring(4, 6));
//					     int day = Integer.parseInt(strDate.substring(6, 8));
//					     String createdate = year+"-"+month+"-"+day;
//					     a.setCreatedate(createdate);
//					}catch(Exception ex){
//					     a.setCreatedate("");
//					}
//				}
//			}
			countTotal = associateService.getTotalCount(associate);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		//通过request绑定对象传到前台
		associate.setTotalCount(countTotal);
		associate.setPageSize(Constants.DEFAULT_PAGE_SIZE); 
		associate.setPageCount(countTotal/Constants.DEFAULT_PAGE_SIZE+1); 
		if (associate.getPageNo() == null)
			associate.setPageNo(1);
		request.setAttribute("associate", associate);
		request.setAttribute("associatelist",associatelist);
		return "web/associate/associateList";
	}	
	
	/**
	 * 社会组织查看编辑
	 * @param associateId
	 * @param request
	 * @param response
	 * @return 
	 */
	@RequestMapping(value = "/associateInfo.do")
	public String associateInfo(
			@RequestParam(value="associateId", required = false)Integer associateId,
			HttpServletRequest request, HttpServletResponse response){ 
		Associate  associate = new Associate();
		if(associateId != null && associateId != 0){
			try{
				associate = associateService.getAssociateById(associateId);
			}catch(Exception ex){
				ex.printStackTrace();
			}
			request.setAttribute("Associate", associate);
		}else{
			associate.setId(associateId);
		}
		List<AssociateType> assoType = new ArrayList<AssociateType>();
		assoType = associateService.getAssociateType();
		request.setAttribute("assoType", assoType);
		return "web/associate/associateInfo";
	}	
}
