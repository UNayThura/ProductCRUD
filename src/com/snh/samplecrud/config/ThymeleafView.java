package com.snh.samplecrud.config;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

@Configuration
@ComponentScan
@EnableWebMvc
public class ThymeleafView {
	
	@Autowired
	ApplicationContext applicationContext;

//	@Bean
//	public SpringResourceTemplateResolver templateResolver(){
//		
//		System.out.println("Thymeleaf resolver");
//		
//		SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
////	    templateResolver.setApplicationContext(this.applicationContext);
//		templateResolver.setApplicationContext(this.);
//	    templateResolver.setPrefix("/WEB-INF/views/");
//	    templateResolver.setSuffix(".html");
//	    
//	    templateResolver.setTemplateMode(TemplateMode.HTML);
//	    
//	    templateResolver.setCacheable(true);
//	    return templateResolver;
//	
//	}
	
	@Bean

	public SpringResourceTemplateResolver templateResolver() {
		SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
		templateResolver.setApplicationContext(this.applicationContext);
	    templateResolver.setPrefix("/WEB-INF/views/");
	    templateResolver.setSuffix(".html");
//	    templateResolver.setTemplateMode("HTML5");
	 
	    return templateResolver;
	}
	
	@Bean
	public SpringTemplateEngine templateEngine(){
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver());
//		templateEngine.setEnableSpringELCompiler(true);
		return templateEngine;
	}
	
	@Bean
	public ViewResolver viewResolver(){
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine());
//		viewResolver.setOrder(1);
//	    viewResolver.setViewNames(new String[] {".html", ".xhtml"});
	    return viewResolver;
	}
}
