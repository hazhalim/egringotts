package net.fitriandfriends.egringotts.dto;

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

    public AccountDTO(String role, String fullName, String gender, Date dateOfBirth, String streetName1, String streetName2, String town, String state, String postcode, String country, String userImageURL, String emailAddress, String username, String password, String telephoneNumber, Long securityQuestionID, String securityAnswer, String securityPIN) {

        this.role = role;
        this.fullName = fullName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.streetName1 = streetName1;
        this.streetName2 = streetName2;
        this.town = town;
        this.state = state;
        this.postcode = postcode;
        this.country = country;
        this.userImageURL = userImageURL;
        this.emailAddress = emailAddress;
        this.username = username;
        this.password = password;
        this.telephoneNumber = telephoneNumber;
        this.securityQuestionID = securityQuestionID;
        this.securityAnswer = securityAnswer;
        this.securityPIN = securityPIN;

    }

}