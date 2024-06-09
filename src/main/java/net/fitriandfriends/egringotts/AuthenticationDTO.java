package net.fitriandfriends.egringotts;

import lombok.Data;

// DTO for sending over authentication details
@Data
public class AuthenticationDTO {

    private String username;
    private String password;

    public AuthenticationDTO() {

        this.username = username;
        this.password = password;

    }

}