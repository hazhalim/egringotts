package net.fitriandfriends.egringotts;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SecurityAnswerRepository extends JpaRepository<SecurityAnswer, Long> {

    public SecurityAnswer findBySecurityAnswerID(Long securityAnswerID);
    public SecurityAnswer findByAccount(Account account);

}