package com.cy.pj.sys.servicea.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.dao.SysUserDao;
import com.cy.pj.sys.service.SysUserService;
import com.cy.pj.sys.vo.SysUserDeptVo;
@Service
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	private SysUserDao sysUserDao;

	@Override
	public PageObject<SysUserDeptVo> findPageObjects(String username, Integer pageCurrent) {
		//1.数据合法性验证
				if(pageCurrent==null||pageCurrent<=0)
				throw new ServiceException("参数不合法");
		//2.依据条件获取总记录数
				int rowCount=sysUserDao.getRowCount(username);
		        if(rowCount==0)
				throw new ServiceException("记录不存在");
				//3.计算startIndex的值
				int pageSize=3;
				int startIndex=(pageCurrent-1)*pageSize;
				//4.依据条件获取当前页数据
				List<SysUserDeptVo> records=sysUserDao.findPageObjects(
				username, startIndex, pageSize);
				//5.封装数据
				PageObject<SysUserDeptVo> pageObject=new PageObject<>();
				pageObject.setPageCurrent(pageCurrent);
				pageObject.setRowCount(rowCount);
				pageObject.setPageSize(pageSize);
				pageObject.setRecords(records);
		pageObject.setPageCount((rowCount-1)/pageSize+1);
				return pageObject;

	}

	/**
	 * 禁用/启用
	 * @return
	 */
	@Override
	public int validById(Integer id, Integer valid, String modifiedUser) {
		if(id==null||id<=0)
			throw new ServiceException("参数不合法,id="+id);
		if(valid!=1&&valid!=0)
			throw new ServiceException("参数不合法,valie="+valid);
		if(StringUtils.isEmpty(modifiedUser))
			throw new ServiceException("修改用户不能为空");
	
		int rows =0;		
		try {
			rows =sysUserDao.validById(id, valid, modifiedUser);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException("底层正在维护");
		}
		
		if(rows == 0)
			throw new ServiceException("此记录可能已经不存在");
		return rows;
	}
	
}
