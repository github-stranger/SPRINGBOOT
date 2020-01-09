package com.cy.pj.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import ch.qos.logback.core.pattern.parser.Node;

@Mapper
public interface SysMenuDao {

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
