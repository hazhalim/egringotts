package net.fitriandfriends.egringotts;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Account {

    // Instance variables
    // Important account details;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountID;

    private String type;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date closingDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLoginDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogoutDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastActivityDate;

    // Account holder information
    private String firstName;
    private String middleName;
    private String lastName;

    private String gender;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfBirth;

    @Embedded
    private Address mailingAddress;

    @Embedded
    private Address billingAddress;

    private User userType;

    // Account access information
    private String emailAddress;
    private String username;
    private String password;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "number", column = @Column(name = "home_telephone_number"))
    })
    private TelephoneNumber homeTelephoneNumber;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "number", column = @Column(name = "mobile_telephone_number"))
    })
    private TelephoneNumber mobileTelephoneNumber;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "number", column = @Column(name = "work_telephone_number"))
    })
    private TelephoneNumber workTelephoneNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "security_question_set_id", referencedColumnName = "id")
    private SecurityQuestionSet securityQuestionSet;

    private int securityPIN;

    // Constructor
    public Account() {}

    public Account(String type, Date lastLoginDate, String firstName, String middleName, String lastName, String gender, Date dateOfBirth, Address mailingAddress, Address billingAddress, User userType, String emailAddress, String username, String password, TelephoneNumber homeTelephoneNumber, TelephoneNumber mobileTelephoneNumber, TelephoneNumber workTelephoneNumber, SecurityQuestionSet securityQuestionSet, int securityPIN) {

        this.type = type;
        this.creationDate = new Date();
        this.closingDate = null;
        this.lastLoginDate = lastLoginDate;
        this.lastLogoutDate = null;
        this.lastActivityDate = null;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.mailingAddress = mailingAddress;
        this.billingAddress = billingAddress;
        this.userType = userType;
        this.emailAddress = emailAddress;
        this.username = username;
        this.password = password;
        this.homeTelephoneNumber = homeTelephoneNumber;
        this.mobileTelephoneNumber = mobileTelephoneNumber;
        this.workTelephoneNumber = workTelephoneNumber;
        this.securityQuestionSet = securityQuestionSet;
        this.securityPIN = securityPIN;

    }

    // Accessor and mutator methods
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getAccountID() {
        return accountID;
    }

    public void setAccountID(Long accountID) {
        this.accountID = accountID;
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

    public Date getLastLogoutDate() {
        return lastLogoutDate;
    }

    public void setLastLogoutDate(Date lastLogoutDate) {
        this.lastLogoutDate = lastLogoutDate;
    }

    public Date getLastActivityDate() {
        return lastActivityDate;
    }

    public void setLastActivityDate(Date lastActivityDate) {
        this.lastActivityDate = lastActivityDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public Address getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(Address mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public User getUserType() {
        return userType;
    }

    public void setUserType(User userType) {
        this.userType = userType;
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

    public TelephoneNumber getHomeTelephoneNumber() {
        return homeTelephoneNumber;
    }

    public void setHomeTelephoneNumber(TelephoneNumber homeTelephoneNumber) {
        this.homeTelephoneNumber = homeTelephoneNumber;
    }

    public TelephoneNumber getMobileTelephoneNumber() {
        return mobileTelephoneNumber;
    }

    public void setMobileTelephoneNumber(TelephoneNumber mobileTelephoneNumber) {
        this.mobileTelephoneNumber = mobileTelephoneNumber;
    }

    public TelephoneNumber getWorkTelephoneNumber() {
        return workTelephoneNumber;
    }

    public void setWorkTelephoneNumber(TelephoneNumber workTelephoneNumber) {
        this.workTelephoneNumber = workTelephoneNumber;
    }

    public SecurityQuestionSet getSecurityQuestionSet() {
        return securityQuestionSet;
    }

    public void setSecurityQuestionSet(SecurityQuestionSet securityQuestionSet) {
        this.securityQuestionSet = securityQuestionSet;
    }

    public int getSecurityPIN() {
        return securityPIN;
    }

    public void setSecurityPIN(int securityPIN) {
        this.securityPIN = securityPIN;
    }

    // Other methods
    @Override
    public String toString() {
        return "Account{" +
                "type='" + type + '\'' +
                ", accountID=" + accountID +
                ", creationDate=" + creationDate +
                ", closingDate=" + closingDate +
                ", lastLoginDate=" + lastLoginDate +
                ", lastLogoutDate=" + lastLogoutDate +
                ", lastActivityDate=" + lastActivityDate +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", mailingAddress=" + mailingAddress +
                ", billingAddress=" + billingAddress +
                ", userType=" + userType +
                ", emailAddress='" + emailAddress + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", homeTelephoneNumber=" + homeTelephoneNumber +
                ", mobileTelephoneNumber=" + mobileTelephoneNumber +
                ", workTelephoneNumber=" + workTelephoneNumber +
                ", securityQuestionSet=" + securityQuestionSet +
                ", securityPIN=" + securityPIN +
                '}';
    }

}