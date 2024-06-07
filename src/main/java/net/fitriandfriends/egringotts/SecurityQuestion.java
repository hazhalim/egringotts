package net.fitriandfriends.egringotts;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Table
@Data
@ToString
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

    // Accessor, mutator, and toString methods are handled by Lombok

}