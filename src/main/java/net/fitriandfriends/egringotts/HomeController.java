package net.fitriandfriends.egringotts;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class HomeController {


    @GetMapping("/login")
    public String home(Principal principal) {

        return "Welcome to E-Gringotts, " + principal.getName() + "!";

    }

    @GetMapping("/dashboard")
    public String dashboard() {

        return "This is the secured version of E-Gringotts!";

    }

    @GetMapping("/admin")
    public String admin() {

        return "This is the admin version of E-Gringotts!";

    }


}