package net.fitriandfriends.egringotts;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class AccountDTO {

    // Instance variables
    private String role;

    private String fullName;
    private String gender;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    private String streetName1;
    private String streetName2;
    private String town;
    private String state;
    private String postcode;
    private String country;

    private String userImageURL;

    private String emailAddress;
    private String username;
    private String password;

    private String telephoneNumber;
    private Long securityQuestionID;
    private String securityAnswer;
    private String securityPIN;

}