package ninckblokje.zin.api;

import ninckblokje.zin.api.properties.ZinApiProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableConfigurationProperties(ZinApiProperties.class)
@EnableJpaAuditing(auditorAwareRef = "userAuditorAware")
public class ZinApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZinApiApplication.class, args);
    }
}
