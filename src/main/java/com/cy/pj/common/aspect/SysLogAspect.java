package com.cy.pj.common.aspect;

import java.util.Date;
import java.lang.reflect.Method;

import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cy.pj.common.annotation.RequestLog;
import com.cy.pj.sys.dao.SysLogDao;
import com.cy.pj.sys.entity.SysLog;
import com.cy.pj.sys.entity.SysUser;
import com.cy.pj.sys.service.SysLogService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/**日志切面实现*/
@Aspect
@Service
@Slf4j
@Component
public class SysLogAspect {
//	/**@Pointcut 注解描述的方法为一个切入点
//	 * 1)bean(bean名字) 为一种定义切入点的表达式
//	 * a)bean名字为spring容器中某个bean或多个bean的名字
//	 * b)bean名字对应的bean中的所有方法的集合为切入点
//	 * */
//    //@Pointcut("bean(*ServiceImpl)")
//	//@Pointcut("@annotation(com.cy.pj.common.annotation.RequiredLog)")
//	//public void logPointCut() {}
//    /**
//     * @Around  注解描述的方法为一个环绕通知,
//     * 此通知可以目标方法执行之前和之后添加
//     * 扩展功能,甚至控制目标方法执行.
//     * @param jp 连接点对象,
//     * ProceedingJoinPoint类型的连接点只能应用在环绕通知,
//     * @return 目标方法的执行结果
//     * @throws Throwable
//     * 环绕通知方法最好抛出Throwable类型异常.
//     */
//    //@Around("bean(sysMenuServiceImpl)")
//    @Around("@annotation(com.cy.pj.common.annotation.RequestLog)")
//    @Pointcut("bean(*ServiceImpl)")
//    public Object around(ProceedingJoinPoint jp)
//    throws Throwable{
//    	long t1=System.currentTimeMillis();
//    	log.info("start:"+t1);
//    	//调用下一个切面或目标方法
//    	Object result=jp.proceed();
//    	long t2=System.currentTimeMillis();
//    	log.info("after:"+t2);
//    	//保存用户行为日志
//    	saveObject(jp,(t2-t1));
//    	return result;
//    }
//    @Autowired
//    private SysLogService sysLogService;
//    /**
//     * 记录：
//     * 谁在什么时间点，执行什么操作，访问什么方法，传递的参数是什么，
//     * 执行时长是多少。
//     * @param jp
//     * @param time
//     * @throws Exception
//     */
//    private void saveObject(ProceedingJoinPoint jp,long time)
//    throws Exception{
//    	//1.获取用户行为日志
//    	//1.1获取方法所在的类及方法名
//    	//1)获取方法签名(封装了方法的相关信息)
//    	MethodSignature ms=(MethodSignature)jp.getSignature();
//    	//2)获取目标对象的字节码对象(通过它可以获取类全名)
//    	Class<?> targetCls=
//    	jp.getTarget().getClass();
//    	//3)获取目标类中的方法(通过其对象获取方法名)
//    	Method targetMethod=ms.getMethod();
//    	String clsMethod=
//        targetCls.getName()+"."+targetMethod.getName();
//    	//1.2获取目标方法执行时的实际参数(通过连接点获取)
//    	Object[] args=jp.getArgs();
//    	ObjectMapper om=new ObjectMapper();//jackson
//    	String params=om.writeValueAsString(args);
//    	//1.3)获取目标方法上的注解及操作名
//    	RequestLog rlog=
//    	targetMethod.getAnnotation(RequestLog.class);
//    	String operation="operation";
//    	if(rlog!=null) {operation=rlog.value();}
//    	//1.4)获取登录用户(此信息在哪存着? session)
//    	SysUser user=(SysUser)
//    	SecurityUtils.getSubject().getPrincipal();
//    	//2.封装用户行为日志信息
////    	SysLog log=new SysLog()
////    	.setIp(IPUtils.getIpAddr())
////    	.setMethod(clsMethod)//类全名+方法名
////    	.setParams(params)//执行方法时传递的实际参数
////    	.setOperation(operation)
////    	.setUsername(user.getUsername())//登陆用户
////    	.setTime(time)
////    	.setCreatedTime(new Date());
//    	
//    	SysLog logs=new SysLog();
//     	logs.setUsername("admin");//登陆的用户
//    	//假如目标方法对象上有注解,我们获取注解定义的操作值
//    	
//    	RequestLog requestLog=
//    	targetMethod.getDeclaredAnnotation(RequestLog.class);
//    	logs.setOperation(requestLog.value());
//   
//    	logs.setMethod(clsMethod);//className.methodName()//类全名+方法名
//    	logs.setParams(params);//method params//执行方法时传递的实际参数
//    	logs.setIp(IPUtils.getIpAddr());//ip 地址
//    	logs.setTime(time);//
//    	logs.setCreatedTime(new Date());
//    	//3.保存日志信息
//
//    	//3.将用户行为日志持久化到数据库
//    	log.info("start:" + new Date());
//    	sysLogService.saveObject(logs);
////    	new Thread() {
////    		public void run() {
////    			sysLogService.saveObject(log);
////    		};
////    	}.start();
//    }

	
	
	
	
	/**
	 * 
	 */
	@Autowired
	private SysLogDao sysLogDao;
	
	/**
	 * @Pointcut注解用于定义切入点 bean表达式为切入点表达式, bean表达式内部指定的bean对象中 所有方法为切入点(进行功能扩展的点)
	 */
	@Pointcut("@annotation(com.cy.pj.common.annotation.RequestLog)")
//	@Pointcut("bean(*ServiceImpl)")
	public void logPointCut(){}
	
	/**
	 * @Around 描述的方法为环绕通知,用于功能增强 环绕通知(目标方法执行之前和之后都可以执行)
	 * @param jp 连接点 (封装了要执行的目标方法信息)
	 * @return 目标方法的执行结果
	 * @throws Throwable
	 */
    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint //连接点
    		jointPoint) throws Throwable{
    	long startTime=System.currentTimeMillis();
    	//执行目标方法(result为目标方法的执行结果)
    	Object result=jointPoint.proceed();
    	long endTime=System.currentTimeMillis();
    	long totalTime=endTime-startTime;
    	log.info("方法执行的总时长为:"+totalTime);
    	saveSysLog(jointPoint,totalTime);
    	return result;
    }
    private void saveSysLog(ProceedingJoinPoint point,
    		  long totleTime) throws NoSuchMethodException, SecurityException, JsonProcessingException{
    	//1.获取日志信息
    	MethodSignature ms=
    	(MethodSignature)point.getSignature();
    	Class<?> targetClass=
    	point.getTarget().getClass();
    	String className=targetClass.getName();
    	//获取接口声明的方法
    	String methodName=ms.getMethod().getName();
    	Class<?>[] parameterTypes=ms.getMethod().getParameterTypes();
    	//获取目标对象方法
    	Method targetMethod=targetClass.getDeclaredMethod(
    			    methodName,parameterTypes);
    	//判定目标方法上是否有RequestLog注解
    	boolean flag=
    	targetMethod.isAnnotationPresent(RequestLog.class);
//    	String username=//此工具类需要学完shiro，AOP再进行自定义实现
//    	ShiroUtils.getPrincipal().getUsername();
    	//获取方法参数
    	Object[] paramsObj=point.getArgs();
    	System.out.println("paramsObj="+paramsObj);
    	//将参数转换为字符串
    	String params=new ObjectMapper()
    	.writeValueAsString(paramsObj);
    	//2.封装日志信息
    	SysLog log=new SysLog();
    	log.setUsername("admin");//登陆的用户
    	//假如目标方法对象上有注解,我们获取注解定义的操作值
    	if(flag){
    	RequestLog requestLog=
    	targetMethod.getDeclaredAnnotation(RequestLog.class);
    	log.setOperation(requestLog.value());
    	}
    	log.setMethod(className+"."+methodName);//className.methodName()
    	log.setParams(params);//method params
    	log.setIp(IPUtils.getIpAddr());//ip 地址
    	log.setTime(totleTime);//
    	log.setCreatedTime(new Date());
    	//3.保存日志信息
    	sysLogDao.insertObject(log);
    }

}
