package com.cy.pj.common.exception;

/**
 * 通过自定义异常可更好的定位错误，确定问题以便提供用户体验。
 * @author Administrator
 *
 */
public class ServiceException extends RuntimeException  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ServiceException() {
		super();
	}
	public ServiceException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	public ServiceException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	} 

}
