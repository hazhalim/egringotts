package net.fitriandfriends.egringotts;

import lombok.Data;

@Data
public class SecurityAnswerDTO {

    // Instance variables
    private Long accountId;
    private Long securityQuestionId;
    private String answer;

    public SecurityAnswerDTO(Long accountId, Long securityQuestionId, String answer) {

        this.accountId = accountId;
        this.securityQuestionId = securityQuestionId;
        this.answer = answer;

    }

}