package hei2017.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by pic on 08/02/2017.
 */
@Configuration
@ComponentScan(basePackages = "hei2017.service")
public class AppConfig extends WebMvcConfigurerAdapter {

    @Bean
    public Properties dbProperties()
    {
        Properties properties = new Properties();

        try
        {
            properties.load(AppConfig.class.getClassLoader().getResourceAsStream("db.properties"));
        }
        catch (IOException ex)
        {

        }
        return properties;
    }
}
