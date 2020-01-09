package com.cy.pj.sys.servicea.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.dao.SysRoleDao;
import com.cy.pj.sys.entity.SysRole;
import com.cy.pj.sys.service.SysRoleService;

@Service
public class SysRoleServiceImpl implements SysRoleService {

	@Autowired
	private SysRoleDao sysRoleDao;

	/**
     * 本方法中要分页查询角色信息,并查询角色总记录数据
     * @param pageCurrent 当表要查询的当前页的页码值
     * @return 封装当前实体数据以及分页信息
     */
	@Override
	public PageObject<SysRole> findPageObjects(String name, Integer pageCurrent) {
		 //1.验证参数合法性
		  //1.1验证pageCurrent的合法性，
		  //不合法抛出IllegalArgumentException异常
		  if(pageCurrent==null||pageCurrent<1)
		  throw new IllegalArgumentException("当前页码不正确");
		  //2.基于条件查询总记录数
		  //2.1) 执行查询
		  int rowCount=sysRoleDao.getRowCount(name);
		  //2.2) 验证查询结果，假如结果为0不再执行如下操作
		  if(rowCount==0)
throw new ServiceException("记录不存在");
		  //3.基于条件查询当前页记录(pageSize定义为2)
		  //3.1)定义pageSize
		  int pageSize=5;
		  //3.2)计算startIndex
		  int startIndex=(pageCurrent-1)*pageSize;
		  //3.3)执行当前数据的查询操作
		  List<SysRole> records=
		  sysRoleDao.findPageObjects(name, startIndex, pageSize);
		  //4.对分页信息以及当前页记录进行封装
		  //4.1)构建PageObject对象
		  PageObject<SysRole> pageObject=new PageObject<>();
		  //4.2)封装数据
		  pageObject.setPageCurrent(pageCurrent);
		  pageObject.setPageSize(pageSize);
		  pageObject.setRowCount(rowCount);
		  pageObject.setRecords(records);
        pageObject.setPageCount((rowCount-1)/pageSize+1);
		  //5.返回封装结果。
		  return pageObject;

	}
	
	
}
