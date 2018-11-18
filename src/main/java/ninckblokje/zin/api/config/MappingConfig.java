package ninckblokje.zin.api.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mapping.context.PersistentEntities;
import org.springframework.data.rest.webmvc.json.DomainObjectReader;
import org.springframework.data.rest.webmvc.mapping.Associations;

@Configuration
public class MappingConfig {

    @Bean
    public DomainObjectReader domainObjectReader(
            PersistentEntities entities,
            Associations associationLinks
    ) {
        return new DomainObjectReader(entities, associationLinks);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
