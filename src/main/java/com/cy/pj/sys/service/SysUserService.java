package com.cy.pj.sys.service;

import org.apache.ibatis.annotations.Param;

import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.vo.SysUserDeptVo;

public interface SysUserService {

	PageObject<SysUserDeptVo> findPageObjects(
			String username,
			Integer pageCurrent);

	/**
	 * 禁用/启用
	 * @return
	 */
	int validById(
			Integer id,
			Integer valid,
			String modifiedUser);
}
