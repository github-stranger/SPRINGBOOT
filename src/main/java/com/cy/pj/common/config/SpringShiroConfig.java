package com.cy.pj.common.config;


import java.util.LinkedHashMap;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Configuration 注解描述的类为一个配置对象,
 * 此对象也会交给spring管理
 */
@Configuration //bean
public class SpringShiroConfig {

	
	/**@Bean 描述的方法,其返回值会交给spring管理
	    * @Bean 一般应用在整合第三bean资源时*/
		 @Bean
		 public SecurityManager newSecurityManager(@Autowired Realm realm) {
			 
			 DefaultWebSecurityManager sManager = new DefaultWebSecurityManager();
			 sManager.setRealm(realm); //类中注入给SecurityManager对象
			return sManager;
		 }

		 @Bean("shiroFilterFactory")
		 public ShiroFilterFactoryBean newShiroFilterFactoryBean(
				 @Autowired SecurityManager securityManager) {
			 ShiroFilterFactoryBean sfBean=
			 new ShiroFilterFactoryBean();
			 sfBean.setSecurityManager(securityManager);
			 
			//假如没有认证请求先访问此认证的url
			 sfBean.setLoginUrl("/doLoginUI");

			 //定义map指定请求过滤规则(哪些资源允许匿名访问,哪些必须认证访问)
			 LinkedHashMap<String,String> map=
					 new LinkedHashMap<>();
			 //静态资源允许匿名访问:"anon"
			 map.put("/bower_components/**","anon");
			 map.put("/build/**","anon");
			 map.put("/dist/**","anon");
			 map.put("/plugins/**","anon");
			 map.put("/user/doLogin","anon"); 	//配置文件中对/user/doLogin.do这个路径进行匿名访问的配置
			 map.put("/doLogout","logout");  //登出
			 //除了匿名访问的资源,其它都要认证("authc")后访问
			 map.put("/**","authc");
			 sfBean.setFilterChainDefinitionMap(map);
			 return sfBean;
		 }

}
