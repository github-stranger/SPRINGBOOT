package com.cy.pj.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PageController {

//	健康检查分析：在浏览器中输入
//	http://localhost:8080/actuator/health
//	假如希望查看更多actuator选项，可以在spring boot中配置文件application.properties中添加如下语句:
//	management.endpoints.web.exposure.include=*
//	http://localhost:8080/actuator/beans 查看所有的spring 容器中的bean信息
	
	/**首页页面*/
	@RequestMapping("/doIndexUI")   //http://localhost:8080/doIndexUI
	public String doIndexUI() {
		return "starter";		
	}
	/**分页div页面*/
	@RequestMapping("/doPageUI")
	public String doPageUi() {
		return "common/page";
		
	}
	
	/***/
	@RequestMapping("{module}/{page}")
	public String doModuleUI(@PathVariable String page) {
		return "sys/"+page;
		
	}
	
	/**登录页面*/
	@RequestMapping("doLoginUI")
	public String doLoginUI(){
			return "login";
	}
}
