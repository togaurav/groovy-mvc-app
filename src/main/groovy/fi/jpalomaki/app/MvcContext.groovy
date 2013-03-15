package fi.jpalomaki.app

import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import static org.springframework.context.annotation.FilterType.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import org.thymeleaf.spring3.SpringTemplateEngine;
import org.thymeleaf.spring3.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

@EnableWebMvc
@Configuration
@ComponentScan(
    useDefaultFilters = false,
    basePackages = ["fi.jpalomaki.app"],
    includeFilters = [
        @Filter(type = ANNOTATION, value = Controller)
    ]
)
class MvcContext extends WebMvcConfigurerAdapter {
    
    @Bean
    def viewResolver() {
        def viewResolver = new ThymeleafViewResolver()
        viewResolver.setTemplateEngine(templateEngine())
        viewResolver
    }
    
    @Bean
    def templateEngine() {
        def templateEngine = new SpringTemplateEngine()
        templateEngine.setTemplateResolver(templateResolver())
        templateEngine
    }
    
    @Bean
    def templateResolver() {
        def templateResolver = new ServletContextTemplateResolver()
        templateResolver.setPrefix("/WEB-INF/views/")
        templateResolver.setSuffix(".html")
        templateResolver.setTemplateMode("HTML5")
        templateResolver
    }

    @Override
    def void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index.html").setViewName("index")
        registry.addViewController("/").setViewName("redirect:/index.html")
    }

    @Override
    def void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable()
    }
}
