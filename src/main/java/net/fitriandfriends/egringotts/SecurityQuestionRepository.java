package net.fitriandfriends.egringotts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SecurityQuestionRepository extends JpaRepository<SecurityQuestion, Long> {

    public List<SecurityQuestion> findAll();
    public SecurityQuestion findBySecurityQuestionID(Long securityQuestionID);
    public SecurityQuestion findByQuestion(String question);

}