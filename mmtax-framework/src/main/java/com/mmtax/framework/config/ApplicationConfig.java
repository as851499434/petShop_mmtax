package com.mmtax.framework.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 程序注解配置
 *
 * @author mmtax
 */
@Configuration
// 表示通过aop框架暴露该代理对象,AopContext能够访问
@EnableAspectJAutoProxy(exposeProxy = true)
// 指定要扫描的Mapper类的包的路径
@MapperScan("com.mmtax.*.mapper")
public class ApplicationConfig
{

}
