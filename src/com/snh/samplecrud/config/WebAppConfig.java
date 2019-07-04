package com.snh.samplecrud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Created by Sai Nyan Htay
 */
@Configuration
@EnableScheduling
@EnableWebMvc
//@EnableAsync
@ComponentScan(basePackages = { "com.snh.samplecrud" })
public class WebAppConfig extends WebMvcConfigurerAdapter {

	@Bean
	public InternalResourceViewResolver resolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setViewClass(JstlView.class);
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".html");
		return resolver;
	}
	
	/**
	 * Configure Cross Origin Request Processing
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		
		registry.addMapping("/**")
			.allowedOrigins("*")
			.allowedMethods("GET","HEAD","OPTIONS","POST","PUT","DELETE")
			.allowedHeaders("Content-Type","Access-Control-Allow-Headers", "Origin","Headers","Authorization")
			.allowCredentials(false).maxAge(3600);

		
	}

}
