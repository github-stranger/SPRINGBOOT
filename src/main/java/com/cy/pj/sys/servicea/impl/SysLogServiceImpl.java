package com.cy.pj.sys.servicea.impl;

import java.util.List;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cy.pj.common.annotation.RequestLog;
import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.dao.SysLogDao;
import com.cy.pj.sys.entity.SysLog;
import com.cy.pj.sys.service.SysLogService;

import lombok.extern.slf4j.Slf4j;
/**日志管理*/
@Slf4j //Logger
@Service
@Transactional
public class SysLogServiceImpl implements SysLogService {

	@Autowired
	private SysLogDao sysLogDao;

	/**日志信息*/
	@RequestLog("日志信息")  //RequestLog注解--记录方法具体操作
	@Override
	public PageObject<SysLog> findPageObjects(String username, Integer pageCurrent) {
		//1.验证参数合法性
		  //1.1验证pageCurrent的合法性，
		  //不合法抛出IllegalArgumentException异常
		  if(pageCurrent==null||pageCurrent<1)
		  throw new IllegalArgumentException("当前页码不正确");
		  //2.基于条件查询总记录数
		  //2.1) 执行查询
		  int rowCount=sysLogDao.getRowCount(username);
		  
	   	  //日志信息
		  log.info("总记录数-rowCount："+rowCount);	  
		  
		  //2.2) 验证查询结果，假如结果为0不再执行如下操作
		  if(rowCount==0)
        throw new ServiceException("系统没有查到对应记录");
		  //3.基于条件查询当前页记录(pageSize定义为2)
		  //3.1)定义pageSize
		  int pageSize=5;
		  //3.2)计算startIndex
		  int startIndex=(pageCurrent-1)*pageSize;
		  //3.3)执行当前数据的查询操作
		  List<SysLog> records=
		  sysLogDao.findPageObjects(username, startIndex, pageSize);
		  //4.对分页信息以及当前页记录进行封装
		  //4.1)构建PageObject对象
		  PageObject<SysLog> pageObject=new PageObject<>();
		  //4.2)封装数据
		  pageObject.setPageCurrent(pageCurrent);
		  pageObject.setPageSize(pageSize);
		  pageObject.setRowCount(rowCount);
		  pageObject.setRecords(records);
        pageObject.setPageCount((rowCount-1)/pageSize+1);
		  //5.返回封装结果。
		  return pageObject;

	}

	/**
	 * 删除
	 */
	@RequestLog("删除日志信息")  //RequestLog注解--记录方法具体操作
	@Override
	public int deleteObjects(Integer... ids) {
		//1.判断参数合法性
		if(ids==null||ids.length==0)
			throw new IllegalArgumentException("请选择一个");
		//2.执行删除操作
		int rows;
		try {
			rows = sysLogDao.deleteObjects(ids);
			//日志信息
			log.info("删除记录数-rows："+rows);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//3.发出报警信息
			throw new ServiceException("");
		}
		//4.对结果进行验证
		if(rows== 0)
			throw new ServiceException("");
		//5.返回结果
		return rows;
	}

	/**
	 * 保存日志
	 * @param entity
	 * @return
	 */
	@Async
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public void saveObject(SysLog entity) {
		sysLogDao.insertObject(entity);
		
	}
}
