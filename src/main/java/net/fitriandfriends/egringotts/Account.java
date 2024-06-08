package net.fitriandfriends.egringotts;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Cascade;

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

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "creation_date")
    private Date creationDate;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "closing_date")
    private Date closingDate;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "last_login_date")
    private Date lastLoginDate;

    // Account holder information
    @Column(name = "full_name")
    private String fullName;

    @Column(name = "gender")
    private String gender;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    private Address address;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
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

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "security_question_id", referencedColumnName = "security_question_id")
    private SecurityQuestion securityQuestion;

    @Column(name = "security_pin")
    private String securityPIN;

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
    public Account(User user, String fullName, String gender, Date dateOfBirth, Address address, UserImage userImage, String emailAddress, String username, String password, String telephoneNumber, SecurityQuestion securityQuestion, String securityPIN) {

        this.user = user;
        this.fullName = fullName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
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
    // Lifecycle callbacks
    @PrePersist
    protected void onCreate() {

        creationDate = new Date();
        lastLoginDate = new Date();

    }

    @PreUpdate
    protected void onUpdate() {

        lastLoginDate = new Date();

    }

}