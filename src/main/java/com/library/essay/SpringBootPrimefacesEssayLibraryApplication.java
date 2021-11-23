package com.library.essay;

import java.util.Arrays;

import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContext;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

//import com.library.essay.MyServletContextInitializer;


@SpringBootApplication
public class SpringBootPrimefacesEssayLibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootPrimefacesEssayLibraryApplication.class, args);
	}
	
//	@Override
//	protected SpringApplicationBuilder configure(SpringBootPrimefacesEssayLibraryApplication application) {
//		return application
//				.sources(new Class[] { SpringBootPrimefacesEssayLibraryApplication.class, MyServletContextInitializer.class });
//	}

	@Bean
	ServletRegistrationBean jsfServletRegistration(ServletContext servletContext) {
		// spring boot only works if this is set
		servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());

		// FacesServlet registrationf
		ServletRegistrationBean srb = new ServletRegistrationBean();
		srb.setServlet(new FacesServlet());
		srb.setUrlMappings(Arrays.asList("*.xhtml"));
		srb.setLoadOnStartup(1);
		return srb;
	}

}
