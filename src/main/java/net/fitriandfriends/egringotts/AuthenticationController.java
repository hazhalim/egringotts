package net.fitriandfriends.egringotts;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signin")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/auth")
    public ResponseEntity<String> signIn(@RequestBody AuthenticationDTO authRequest, HttpServletRequest request, HttpServletResponse response) {

        try {

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );

            // If authentication is successful, return a success message
            // Retrieve UserDetails object from authentication
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            // Retrieve authority/authorities from UserDetails
            String authority = userDetails.getAuthorities().iterator().next().getAuthority();

            return ResponseEntity.ok().body(authority);

        } catch (AuthenticationException exception) {

            // Handle authentication failure
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

        }

    }

    // Additional controller methods

}