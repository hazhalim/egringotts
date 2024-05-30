package net.fitriandfriends.egringotts;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table
public class Account {

    // Instance variables
    // Important account details
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountID;

    @OneToOne
    @JoinColumn(name = "userID", referencedColumnName = "userID")
    private User user;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date closingDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLoginDate;

    // Account holder information
    private String fullName;

    private String gender;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfBirth;

//    List<Transaction> transactions;

    @OneToOne
    @JoinColumn(name = "addressID", referencedColumnName = "addressID")
    private Address addressID;

    @OneToOne
    @JoinColumn(name = "userImageID", referencedColumnName = "userImageID")
    private UserImage userImage;

    // Account access information
    private String emailAddress;
    private String username;
    private String password;

    private String telephoneNumber;
    @ManyToOne
    @JoinColumn(name = "securityQuestionID", referencedColumnName = "securityQuestionID")
    private SecurityQuestion securityquestion;

    private int securityPIN;

    // Constructors
    public Account() {}

    public Account(User user, String fullName, String gender, Date dateOfBirth, Address addressID, UserImage userImage, String emailAddress, String username, String password, String telephoneNumber, SecurityQuestion securityquestion, int securityPIN) {
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
        this.securityquestion = securityquestion;
        this.securityPIN = securityPIN;
    }

    public Long getAccountID() {
        return accountID;
    }

    public void setAccountID(Long accountID) {
        this.accountID = accountID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(Date closingDate) {
        this.closingDate = closingDate;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Address getAddressID() {
        return addressID;
    }

    public void setAddressID(Address addressID) {
        this.addressID = addressID;
    }

    public UserImage getUserImage() {
        return userImage;
    }

    public void setUserImage(UserImage userImage) {
        this.userImage = userImage;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public SecurityQuestion getSecurityquestion() {
        return securityquestion;
    }

    public void setSecurityquestion(SecurityQuestion securityquestion) {
        this.securityquestion = securityquestion;
    }

    public int getSecurityPIN() {
        return securityPIN;
    }

    public void setSecurityPIN(int securityPIN) {
        this.securityPIN = securityPIN;
    }
}