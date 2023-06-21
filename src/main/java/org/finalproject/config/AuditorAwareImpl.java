package org.finalproject.config;



import org.finalproject.entities.User;
import org.finalproject.service.DefaultUserService;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AuditorAwareImpl implements AuditorAware<User> {
    private DefaultUserService userService;
    public Optional< User> getCurrentAuditor() {

         /*   return Optional.ofNullable(SecurityContextHolder.getContext())
                    .map(SecurityContext::getAuthentication)
                    .filter(Authentication::isAuthenticated)
                    .map(Authentication::getPrincipal)
                    .map(User.class::cast);
        }*/
        return Optional.of(userService.getOne(100L)) ;
    }




}

