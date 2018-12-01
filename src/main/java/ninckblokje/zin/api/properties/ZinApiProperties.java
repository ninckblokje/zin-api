package ninckblokje.zin.api.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties("zin.api")
@Validated
public class ZinApiProperties {

    private Resource userFile;

    public Resource getUserFile() {
        return userFile;
    }

    public void setUserFile(Resource userFile) {
        this.userFile = userFile;
    }
}
