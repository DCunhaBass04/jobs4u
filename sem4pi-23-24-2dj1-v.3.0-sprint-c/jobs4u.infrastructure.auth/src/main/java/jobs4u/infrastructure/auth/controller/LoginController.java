package jobs4u.infrastructure.auth.controller;

import jobs4u.infrastructure.auth.services.Jobs4uUserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginController {

    @Autowired
    private Jobs4uUserAuthService authService;

    public boolean authenticate(String rawUsername, String rawPassword) {
        return authService.authenticate(rawUsername, rawPassword);
    }

}
