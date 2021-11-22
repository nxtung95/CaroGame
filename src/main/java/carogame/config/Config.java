package carogame.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Config implements WebMvcConfigurer {
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**")
				.addResourceLocations("classpath:css/");
		registry.addResourceHandler("/js/**")
				.addResourceLocations("classpath:js/");
		registry.addResourceHandler("/img/**")
				.addResourceLocations("classpath:img/");
		registry.addResourceHandler("/font-awesome/**")
				.addResourceLocations("classpath:font-awesome/");
	}

	@Bean
	public RequestContextListener requestContextListener() {
		return new RequestContextListener();
	}
}
