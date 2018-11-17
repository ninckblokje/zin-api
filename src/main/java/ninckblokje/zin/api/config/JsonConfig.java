package ninckblokje.zin.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mapping.context.PersistentEntities;
import org.springframework.data.rest.webmvc.json.DomainObjectReader;
import org.springframework.data.rest.webmvc.mapping.Associations;

@Configuration
public class JsonConfig {

    @Bean
    public DomainObjectReader domainObjectReader(
            PersistentEntities entities,
            Associations associationLinks
    ) {
        return new DomainObjectReader(entities, associationLinks);
    }
}
