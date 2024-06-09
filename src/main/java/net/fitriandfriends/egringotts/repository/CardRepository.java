package net.fitriandfriends.egringotts.repository;

import net.fitriandfriends.egringotts.base.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    public Card findByCardID(Long cardID);

    public List<Card> findByAccount_AccountID(Long accountID);

    public List<Card> findByAccount_AccountIDAndType(Long accountId, String cardType);

    public List<Card> findAll();

}