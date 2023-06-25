package org.finalproject.config;



import org.finalproject.entities.User;
import org.finalproject.service.DefaultUserService;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AuditorAwareImpl implements AuditorAware<String> {

    public Optional< String > getCurrentAuditor() {
        // until there is no authentification
        // return Optional.of("Alex Smith")

        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());


    }

}

