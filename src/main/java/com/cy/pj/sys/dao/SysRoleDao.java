package com.cy.pj.sys.dao;

import java.util.List;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cy.pj.sys.entity.SysRole;

@Mapper
public interface SysRoleDao {

	 /**
     * 分页查询角色信息
     * @param startIndex 上一页的结束位置
     * @param pageSize 每页要查询的记录数
     * @return
     */
	List<SysRole> findPageObjects(
			@Param("name")String name,
			@Param("startIndex")Integer startIndex,
			@Param("pageSize")Integer pageSize);
	
	/**
	 * 查询记录总数
	 * @return
	 */
	int getRowCount(@Param("name")String name);
}
