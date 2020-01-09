package com.cy.pj.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cy.pj.common.vo.JsonResult;
import com.cy.pj.sys.service.SysMenuService;

@Controller
@RequestMapping("/menu/")
public class SysMenuController {

	@Autowired
	private SysMenuService sysMenuService;
	
	/**
	 * 菜单信息呈现
	 * @return
	 */
	@RequestMapping("doFindObjects")
	@ResponseBody
	public JsonResult doFindObjects() {
		
		return new JsonResult(sysMenuService.findObjects());	
	}
	
	/**
	 * 请求获取数据库对应的菜单表中的所有菜单信息(id,name,parentId)
	 * @return
	 */
	@RequestMapping("/doFindZtreeMenuNodes")
	@ResponseBody
	public JsonResult doFindZtreeMenuNodes() {
	
		return new JsonResult(sysMenuService.findZtreeMenuNodes());
		
	}
	
	
	
	
	
	
	
	
}
