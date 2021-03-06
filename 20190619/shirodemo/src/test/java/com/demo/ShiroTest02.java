package com.demo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Before;
import org.junit.Test;

public class ShiroTest02 {
    private SecurityManager securityManager;
    @Before
    public void init() throws Exception{
        //1.加载ini配置文件创建SecurityManager
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");
        //2.获取securityManager
        SecurityManager securityManager = factory.getInstance();
        //13.将securityManager绑定到当前运行环境
        SecurityUtils.setSecurityManager(securityManager);
    }
    @Test
    public void testLogin() throws Exception{
        //1.创建主体(此时的主体还为经过认证)
        Subject subject = SecurityUtils.getSubject();
        //2.构造主体登录的凭证（即用户名/密码）
        UsernamePasswordToken upToken = new UsernamePasswordToken("lisi","123456");
        //3.主体登录
        subject.login(upToken);
        //登录成功验证是否具有role1角色
        //System.out.println("当前用户具有role1="+subject.hasRole("role3"));
        //登录成功验证是否具有某些权限
        System.out.println("当前用户具有user:save权限="+subject.isPermitted("user:save"));
    }
}
