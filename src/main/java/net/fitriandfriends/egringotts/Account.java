package net.fitriandfriends.egringotts;

import jakarta.persistence.*;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Entity
@Table
@Data
@ToString
public class Account {

    // Instance variables
    // Important account details
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long accountID;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date")
    private Date creationDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "closing_date")
    private Date closingDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_login_date")
    private Date lastLoginDate;

    // Account holder information
    @Column(name = "full_name")
    private String fullName;

    @Column(name = "gender")
    private String gender;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    private Address addressID;

    @OneToOne
    @JoinColumn(name = "user_image_id", referencedColumnName = "user_image_id")
    private UserImage userImage;

    // Account access information
    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "telephone_number")
    private String telephoneNumber;

    @ManyToOne
    @JoinColumn(name = "security_question_id", referencedColumnName = "security_question_id")
    private SecurityQuestion securityQuestion;

    @Column(name = "security_pin")
    private int securityPIN;

    // Constructors
    public Account() {}

    // Dummy account constructors for search function
    public Account(String fullName, boolean isFullName) {

        this.fullName = fullName;

    }

    public Account(String telephoneNumber) {

        this.telephoneNumber = telephoneNumber;

    }

    public Account(String fullName, String telephoneNumber) {

        this.fullName = fullName;
        this.telephoneNumber = telephoneNumber;

    }

    // Actual constructor
    public Account(User user, String fullName, String gender, Date dateOfBirth, Address addressID, UserImage userImage, String emailAddress, String username, String password, String telephoneNumber, SecurityQuestion securityQuestion, int securityPIN) {

        this.user = user;
        this.fullName = fullName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.addressID = addressID;
        this.userImage = userImage;
        this.emailAddress = emailAddress;
        this.username = username;
        this.password = password;
        this.telephoneNumber = telephoneNumber;
        this.securityQuestion = securityQuestion;
        this.securityPIN = securityPIN;

    }

    // Accessor, mutator, and toString methods are handled by Lombok

    // Other methods

}