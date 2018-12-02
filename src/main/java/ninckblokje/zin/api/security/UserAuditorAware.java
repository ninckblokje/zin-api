package ninckblokje.zin.api.security;

import ninckblokje.zin.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserAuditorAware implements AuditorAware<String> {

    @Autowired
    private UserService userService;

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.ofNullable(userService.getUserId());
    }
}
