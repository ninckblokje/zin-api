package ninckblokje.zin.api.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public String getUserId() {
        return DigestUtils.sha256Hex(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
