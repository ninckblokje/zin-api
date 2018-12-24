package ninckblokje.zin.api.security;

import ninckblokje.zin.api.service.UserService;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserAuditorAware implements AuditorAware<String> {

    private UserService userService;

    public UserAuditorAware(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.ofNullable(userService.getUserId());
    }
}
