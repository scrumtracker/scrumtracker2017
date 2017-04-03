package hei2017.app;

import hei2017.config.ThymeleafConfig;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by pic on 08/02/2017.
 */

@Configuration
@ComponentScan("hei2017")
@EnableWebMvc
@Import({ThymeleafConfig.class})
public class ApplicationContext extends WebMvcConfigurerAdapter{

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**")
                .addResourceLocations("/static/css/");
        registry.addResourceHandler("/js/**")
                .addResourceLocations("/static/js/");
        registry.addResourceHandler("/fonts/**")
                .addResourceLocations("/static/fonts/");
        registry.addResourceHandler("/img/**")
                .addResourceLocations("/static/img/");
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties()
    {
        PropertySourcesPlaceholderConfigurer propertySources = new PropertySourcesPlaceholderConfigurer();
        //propertySources.setIgnoreUnresolvablePlaceholders(true);
        return propertySources;
    }

    @Bean
    public ResourceBundleMessageSource messageSource()
    {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasename("locale/messages");
        source.setDefaultEncoding("UTF-8");
        return source;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.indentOutput(true);
        converters.add(new MappingJackson2HttpMessageConverter(builder.build()));
    }
}
