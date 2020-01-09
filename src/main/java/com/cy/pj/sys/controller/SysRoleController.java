package com.cy.pj.sys.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cy.pj.common.vo.JsonResult;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.entity.SysRole;
import com.cy.pj.sys.service.SysRoleService;

@Controller
@RequestMapping("/role/")
public class SysRoleController {

	@Autowired
	private SysRoleService sysRoleService;
	
	/**
	 * 分页查询角色信息
	 * @return
	 */
	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(String name,Integer pageCurrent) {
		PageObject<SysRole> pageObject =sysRoleService.findPageObjects(name, pageCurrent);
		return new JsonResult(pageObject);
	}
	
}
