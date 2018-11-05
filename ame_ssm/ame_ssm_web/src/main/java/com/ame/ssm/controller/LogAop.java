package com.ame.ssm.controller;

import com.ame.ssm.domain.SysLog;
import com.ame.ssm.domain.UserInfo;
import com.ame.ssm.service.ISysLogService;
import com.ame.ssm.utils.DateUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;


@Component
@Aspect
public class LogAop {

    @Autowired
    private ISysLogService sysLogService;

    @Autowired
    private HttpServletRequest request;

    private Date visitTime; // 访问时间
    private Class executionClass;// 访问的类
    private Method executionMethod; // 访问的方法 // 主要获取访问时间、访问的类、访问的方法

    @Before("execution(* com.ame.ssm.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws Exception {
        visitTime = new Date(); // 访问时间
        // 获取访问的类
        executionClass = jp.getTarget().getClass();
        // 获取访问的方法
        String methodName = jp.getSignature().getName();// 获取访问的方法的名称

        Object[] args = jp.getArgs();// 获取访问的方法的参数
        if (args == null||args.length == 0){    //如果没有参数，就获得无参方法
            executionMethod = executionClass.getMethod(methodName);
        }else{      //有参数，就将args中所有元素遍历，获取对应的Class，装进Class[]
            Class[] c = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                c[i] = (Class) args[i];
            }
            executionMethod = executionClass.getMethod(methodName,c);
        }
    }

    @After("execution(* com.ame.ssm.controller.*.*(..))")
    public void doAfter(JoinPoint jp) throws Exception {
        long executionTime = new Date().getTime() - visitTime.getTime(); //获取访问时长
        //获取url
        String url = null;
        if (executionClass != null&&executionMethod != null&&executionClass != LogAop.class){
            //获取类上@RequsetMapping注解的值
            RequestMapping classAnnotation = (RequestMapping) executionClass.getAnnotation(RequestMapping.class);
            if (classAnnotation != null){
                String[] classValue = classAnnotation.value();
                //获取方法上的@RequsetMapping注解的值
                RequestMapping methodAnnotation = executionMethod.getAnnotation(RequestMapping.class);
                if (methodAnnotation != null){
                    String[] methodValue = methodAnnotation.value();
                    url = classValue[0]+methodValue[0];
                }
            }
        }
        //获取访问的ip
        String ip = request.getRemoteAddr();
        //获取当前操作的用户
        SecurityContext context = SecurityContextHolder.getContext();//从上下文中获取当前登陆的用户
        User principal = (User) context.getAuthentication().getPrincipal();
        String username = principal.getUsername();

        SysLog sysLog = new SysLog();
        sysLog.setExecutionTime(executionTime);
        sysLog.setIp(ip);
        sysLog.setMethod("[类名] "+executionClass.getName()+"[方法名] "+executionMethod.getName());
        sysLog.setUrl(url);
        sysLog.setUsername(username);
        sysLog.setVisitTime(visitTime);

        //调用service完成操作
        sysLogService.save(sysLog);
    }
}
