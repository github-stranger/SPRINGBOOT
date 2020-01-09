package com.cy.pj.sys.service;

import java.util.List;
import java.util.Map;

import ch.qos.logback.core.pattern.parser.Node;

public interface SysMenuService {


	/**
	 * 菜单数据呈现
	 * @return
	 */
	List<Map<String,Object>> findObjects();
	
	/**
	 * 请求获取数据库对应的菜单表中的所有菜单信息(id,name,parentId)
	 * @return
	 */
	List<Node> findZtreeMenuNodes();
}
