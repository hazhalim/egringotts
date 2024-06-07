package net.fitriandfriends.egringotts;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table
@Data
@Setter
@Getter
@ToString
public class SecurityAnswer {

    // Instance variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "security_answer_id")
    private Long securityAnswerID;

    @OneToOne
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "security_question_id", referencedColumnName = "security_question_Id")
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

    // Accessor, mutator, and toString methods are handled by Lombok

}