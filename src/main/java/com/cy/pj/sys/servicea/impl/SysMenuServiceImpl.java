package com.cy.pj.sys.servicea.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.pj.common.annotation.RequestLog;
import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.sys.dao.SysMenuDao;
import com.cy.pj.sys.service.SysMenuService;

import ch.qos.logback.core.pattern.parser.Node;
@Service
public class SysMenuServiceImpl implements SysMenuService {

	@Autowired
	private SysMenuDao sysMenuDao;

	/**
	 * 菜单数据呈现
	 * @return
	 */
	@RequestLog("菜单信息") //RequestLog注解--记录方法具体操作
	@Override
	public List<Map<String, Object>> findObjects() {
		List<Map<String, Object>> list = sysMenuDao.findObjects();
		if(list==null||list.size()==0)
			throw new ServiceException("没有对应的菜单信息");
		return list;
	}

	/**
	 * 请求获取数据库对应的菜单表中的所有菜单信息(id,name,parentId)
	 * @return
	 */
	@Override
	public List<Node> findZtreeMenuNodes() {
		List<Node> list = sysMenuDao.findZtreeMenuNodes();
		if(list==null||list.size()==0)
			throw new ServiceException("没有对应菜单信息");
		return list;
	}
	
}
