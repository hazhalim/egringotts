package net.fitriandfriends.egringotts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private AccountRepository accountRepository;

    public Card getCardByCardId(Long cardId) {

        return cardRepository.findByCardID(cardId);

    }

    public List<Card> getCardsByAccountId(Long accountId) {

        return cardRepository.findByAccount_AccountID(accountId);

    }

    public List<Card> getCardsByAccountIdAndCardType(Long accountId, String type) {

        return cardRepository.findByAccount_AccountIDAndType(accountId, type);

    }

    public List<Card> getAllCards() {

        return cardRepository.findAll();

    }

    public String addCard(CardDTO cardDTO) throws ParseException {

        Card card = new Card();
        card.setType(cardDTO.getType());
        card.setAccount(accountRepository.findByAccountID(cardDTO.getAccountID()));
        card.setCardNumber(cardDTO.getCardNumber());
        card.setCvv(cardDTO.getCvv());

        String expiryDate = cardDTO.getExpiryDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-yyyy");
        card.setExpiryDate(dateFormat.parse(expiryDate));

        cardRepository.save(card);

        return "Card added successfully.";
    }

}