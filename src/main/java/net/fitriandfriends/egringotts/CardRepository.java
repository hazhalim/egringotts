package net.fitriandfriends.egringotts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    public Card findByCardId(Long cardId);

    public List<Card> findByAccountId(Long accountId);

    public List<Card> findByAccountIdAndCardType(Long accountId, String cardType);

    public List<Card> findAll();

}