package app.cloudkitchen.menuservice.configuration;

import org.springframework.data.domain.AuditorAware;

import org.springframework.stereotype.Component;

import java.util.Optional;

// Auditor implementation
//@Component
//public class SpringSecurityAuditorAware implements AuditorAware<String> {
//
//    @Override
//    public Optional<String> getCurrentAuditor() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        if (authentication == null || !authentication.isAuthenticated()
//                || authentication instanceof AnonymousAuthenticationToken) {
//            return Optional.of("system");
//        }
//
//        return Optional.of(authentication.getName());
//    }
//}