package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
public class CustomConfig {

	@Bean
	InternalResourceViewResolver jspView() {
		InternalResourceViewResolver vr = new InternalResourceViewResolver();
		vr.setPrefix("/WEB-INF/");
		vr.setSuffix(".jsp");
		vr.setViewNames("jsp/*");
		vr.setViewClass(JstlView.class);
		
		return vr;
	}
	
}
