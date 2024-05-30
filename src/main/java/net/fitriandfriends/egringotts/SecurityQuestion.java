package net.fitriandfriends.egringotts;

import jakarta.persistence.*;

@Entity
@Table
public class SecurityQuestion {

    // Instance variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "security_question_id")
    private Long securityQuestionID;

    @Column(name = "question")
    private String question;

    // Constructors
    public SecurityQuestion() {
    }

    public SecurityQuestion(String question) {

        this.question = question;

    }

    // Accessor and mutator methods
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

    // Other methods
    @Override
    public String toString() {
        return "SecurityQuestion{" +
                "question='" + question + '\'' +
                '}';
    }

}