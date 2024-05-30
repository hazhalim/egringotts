package net.fitriandfriends.egringotts;

import jakarta.persistence.*;

@Entity
@Table
public class SecurityAnswer {

    // Instance variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "securityAnswerID")
    private Long securityAnswerID;

    @OneToOne
    @JoinColumn(name = "accountID", referencedColumnName = "accountID")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "securityQuestionID", referencedColumnName = "securityQuestionID")
    private SecurityQuestion securityQuestion;

    @Column(name = "answer")
    private String answer;

    // Constructors
    public SecurityAnswer() {}

    public SecurityAnswer(Account account, SecurityQuestion securityQuestion, String answer) {

        this.account = account;
        this.securityQuestion = securityQuestion;
        this.answer = answer;

    }

    // Accessor and mutator methods
    public Long getSecurityAnswerID() {
        return securityAnswerID;
    }

    public void setSecurityAnswerID(Long securityAnswerID) {
        this.securityAnswerID = securityAnswerID;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public SecurityQuestion getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(SecurityQuestion securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    // Other methods
    @Override
    public String toString() {
        return "SecurityAnswer{" +
                "securityAnswerID=" + securityAnswerID +
                ", account=" + account +
                ", securityQuestion=" + securityQuestion +
                ", answer='" + answer + '\'' +
                '}';
    }

}