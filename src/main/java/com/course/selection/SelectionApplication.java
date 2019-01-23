package com.course.selection;

import com.course.selection.config.RewriteFilter;
import com.github.pagehelper.PageHelper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.util.Properties;

@SpringBootApplication
//@MapperScan("com.course.selection.dao")
public class SelectionApplication {
	//配置mybatis的分页插件pageHelper
	@Bean
	public PageHelper pageHelper(){
		PageHelper pageHelper = new PageHelper();
		Properties properties = new Properties();
		properties.setProperty("offsetAsPageNum","true");
		properties.setProperty("rowBoundsWithCount","true");
		properties.setProperty("reasonable","true");
		properties.setProperty("dialect","mysql");    //配置mysql数据库的方言
		pageHelper.setProperties(properties);
		return pageHelper;
	}

	public static void main(String[] args) {
		SpringApplication.run(SelectionApplication.class, args);
	}
	@Bean
	public FilterRegistrationBean testFilterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(new RewriteFilter());//注册rewrite过滤器
		registration.addUrlPatterns("/*");
		registration.addInitParameter(RewriteFilter.REWRITE_TO,"/index.html");
		registration.addInitParameter(RewriteFilter.REWRITE_PATTERNS, "/ui/*");
		registration.setName("rewriteFilter");
		registration.setOrder(1);
		return registration;
	}

}

