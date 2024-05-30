package net.fitriandfriends.egringotts;

import jakarta.persistence.*;

@Entity
@Table
public class SecurityAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long securityAnswerID;

    @OneToOne
    @JoinColumn(name = "accountID", referencedColumnName = "accountID")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "securityQuestionID", referencedColumnName = "securityQuestionID")
    private SecurityQuestion securityQuestion;

    private String answer;
}
