package net.fitriandfriends.egringotts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private AccountRepository accountRepository;

    public Card getCardByCardId(Long cardId) {

        return cardRepository.findByCardId(cardId);

    }

    public List<Card> getCardsByAccountId(Long accountId) {

        return cardRepository.findByAccountId(accountId);

    }

    public List<Card> getCardsByAccountIdAndCardType(Long accountId, String cardType) {

        return cardRepository.findByAccountIdAndCardType(accountId, cardType);

    }

    public List<Card> getAllCards() {

        return cardRepository.findAll();

    }

    public String addCard(CardDTO cardDTO) {

        private Long accountID;
        private String type;
        private String cardNumber;
        private String cvv;
        private String expiryDate;

        Card card = new Card();
        card.setAccount(accountRepository.findByAccountId(cardDTO.getAccountID());

        card.setCardType(cardDTO.getCardType());
        card.setCardNumber(cardDTO.getCardNumber());
        card.setCardHolderName(cardDTO.getCardHolderName());
        card.setExpiryDate(cardDTO.getExpiryDate());
        card.setCvv(cardDTO.getCvv());
        card.setCardStatus(cardDTO.getCardStatus());

        cardRepository.save(card);

        return "Card added successfully.";
    }

}