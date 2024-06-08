package net.fitriandfriends.egringotts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecurityQuestionRepository extends JpaRepository<SecurityQuestion, Long> {

    public SecurityQuestion findBySecurityQuestionID(Long securityQuestionID);
    public SecurityQuestion findByQuestion(String question);

}