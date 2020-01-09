package com.cy.pj.sys.service;

import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.entity.SysLog;
/**日志管理*/
public interface SysLogService{
	 /**
     * 通过此方法实现分页查询操作
     * @param name 基于条件查询时的参数名
     * @param pageCurrent 当前的页码值
     * @return 当前页记录+分页信息
     */
	 PageObject<SysLog> findPageObjects(
			 String username,
			 Integer pageCurrent);

	 /**
	  * 删除
	  * @param ids
	  * @return
	  */
	 int deleteObjects(Integer ...ids);
	 
	 /**
		 * 保存日志
		 * @param entity
		 * @return
		 */
		void saveObject(SysLog entity);
}
