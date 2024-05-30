package net.fitriandfriends.egringotts;

import jakarta.persistence.*;

@Entity
@Table
public class SecurityQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long securityQuestionID;
    private String question;

    public SecurityQuestion() {
    }

    public SecurityQuestion(Long securityQuestionID, String question) {
        this.securityQuestionID = securityQuestionID;
        this.question = question;
    }

    public Long getSecurityQuestionID() {
        return securityQuestionID;
    }

    public void setSecurityQuestionID(Long securityQuestionID) {
        this.securityQuestionID = securityQuestionID;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
