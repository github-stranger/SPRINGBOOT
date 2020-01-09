package com.cy.pj.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cy.pj.common.vo.JsonResult;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.entity.SysLog;
import com.cy.pj.sys.service.SysLogService;
/**日志管理*/
@Controller
//@RestController  //== @ResponseBody+@Controller
@RequestMapping("/log/")
public class SysLogController {

	@Autowired
	private SysLogService sysLogService;
	
	/**
	 * 基于条件分页查询日志信息
	 * @param username  查询条件(例如查询哪个用户的日志信息)
	 * @param pageCurrent
	 * @return
	 */
	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(String username,Integer pageCurrent){
	 PageObject<SysLog> pageObject=
		sysLogService.findPageObjects(username,pageCurrent);
	return new JsonResult(pageObject);
	}

	/**
	 * 删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("doDeleteObjects")
	@ResponseBody
	public JsonResult doDeleteObjects(Integer...ids) {
		sysLogService.deleteObjects(ids);
		return new JsonResult("delete ok");
		
	}
}
