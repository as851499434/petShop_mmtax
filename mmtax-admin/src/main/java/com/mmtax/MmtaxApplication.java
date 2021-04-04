package com.mmtax;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 启动程序
 * 
 * @author mmtax
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@ServletComponentScan
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableTransactionManagement(proxyTargetClass = true)
@ImportResource(locations={"classpath:kaptcha.xml"})
public class MmtaxApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(MmtaxApplication.class, args);
    }
}