package net.fitriandfriends.egringotts.repository;

import net.fitriandfriends.egringotts.base.Account;
import net.fitriandfriends.egringotts.base.SecurityAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecurityAnswerRepository extends JpaRepository<SecurityAnswer, Long> {

    public SecurityAnswer findBySecurityAnswerID(Long securityAnswerID);
    public SecurityAnswer findByAccount(Account account);

}