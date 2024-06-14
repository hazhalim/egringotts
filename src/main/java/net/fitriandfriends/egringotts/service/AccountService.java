package net.fitriandfriends.egringotts.service;

import net.fitriandfriends.egringotts.dto.AccountDTO;
import net.fitriandfriends.egringotts.base.*;
import net.fitriandfriends.egringotts.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private SecurityQuestionRepository securityQuestionRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SecurityAnswerRepository securityAnswerRepository;


    @CacheEvict(value = {"accountById", "accountsSortedAscending", "accountsSortedDescending", "accountsByFullName", "accountsByTelephoneNumber"}, allEntries = true)
    public Account createAccount(AccountDTO accountDTO) {

        // Create a new user (role) and initialise it based on the string provided in accountDTO
        User newUser = switch (accountDTO.getRole()) {

            case "Platinum Patronus" -> new PlatinumPatronus();
            case "Golden Galleon" -> new GoldenGalleon();
            case "Silver Snitch" -> new SilverSnitch();
            case "Goblin" -> new Goblin();
            default -> new SilverSnitch();

        };

        userRepository.save(newUser); // Save the new user to the database

        // Create a new Address object based on details provided in accountDTO
        Address newAddress = new Address(accountDTO.getStreetName1(), accountDTO.getStreetName2(), accountDTO.getTown(), accountDTO.getState(), accountDTO.getPostcode(), accountDTO.getCountry());
        addressRepository.save(newAddress); // Save the new address to the database

        // Attach the SecurityQuestion to the account based on the securityQuestionID provided in accountDTO
        SecurityQuestion securityQuestion = securityQuestionRepository.findBySecurityQuestionID(accountDTO.getSecurityQuestionID());

        // Create a new Account object based on details provided in accountDTO and the new objects created above
        Account account = new Account(newUser, accountDTO.getFullName(), accountDTO.getGender(), accountDTO.getDateOfBirth(), newAddress, accountDTO.getEmailAddress(), accountDTO.getUsername(), accountDTO.getPassword(), accountDTO.getTelephoneNumber(), securityQuestion, accountDTO.getSecurityPIN());

        // Save the new account to the database
        return accountRepository.save(account);

    }

    @Cacheable("accountById")
    public Account getAccountByAccountId(Long accountId) {

        return accountRepository.findByAccountID(accountId);

    }

    // Get all accounts sorted by a certain criteria in ascending order
    @Cacheable("accountsSortedAscending")
    public List<Account> getAllAccountsSortedAscending(String sortBy) {

        Sort sort = Sort.by(sortBy).ascending();

        return accountRepository.findAll(sort);

    }

    // Get all accounts sorted by a certain criteria in descending order
    @Cacheable("accountsSortedDescending")
    public List<Account> getAllAccountsSortedDescending(String sortBy) {

        Sort sort = Sort.by(sortBy).descending();

        return accountRepository.findAll(sort);

    }

    // Search for any account by full name
    @Cacheable("accountsByFullName")
    public List<Account> searchMatchingAccountsByFullName(String fullName) {

        return accountRepository.findByFullNameContainingIgnoreCase(fullName);

    }

    public Optional<Account> getAccountByUsername(String username) {

        return accountRepository.findByUsernameIgnoreCase(username);

    }

    // Search for any account by telephone number
    @Cacheable("accountsByTelephoneNumber")
    public List<Account> searchMatchingAccountsByTelephoneNumber(String telephoneNumber) {

        return accountRepository.findByTelephoneNumberContaining(telephoneNumber);

    }

    // Search for any account by full name or telephone number
    public List<Account> searchMatchingAccounts(String fullName, String telephoneNumber) {

        List<Account> matchingAccounts = new ArrayList<>();

        if (fullName != null && !fullName.isEmpty()) {

            matchingAccounts.addAll(searchMatchingAccountsByFullName(fullName));

        }

        if (telephoneNumber != null && !telephoneNumber.isEmpty()) {

            matchingAccounts.addAll(searchMatchingAccountsByTelephoneNumber(telephoneNumber));

        }

        return matchingAccounts;

    }

    public boolean verifySecurityPIN(Long accountId, String inputSecurityPIN) {

        // Retrieve the account from the database
        Account account = accountRepository.findById(accountId).orElse(null);

        if (account != null) {

            // Retrieve the stored hashed security PIN from the account
            String hashedSecurityPIN = account.getSecurityPIN();

            // Compare the hashed input security PIN with the stored hashed security PIN
            return passwordEncoder.matches(inputSecurityPIN, hashedSecurityPIN);
        }

        return false;

    }

    public AccountDTO getAccountSettings(Long accountId) {

        Account account = accountRepository.findByAccountID(accountId);
        SecurityAnswer securityAnswer = securityAnswerRepository.findByAccount(account);

        return new AccountDTO(account.getUser().getType(), account.getFullName(), account.getGender(), account.getDateOfBirth(), account.getAddress().getStreetName1(), account.getAddress().getStreetName2(), account.getAddress().getTown(), account.getAddress().getState(), account.getAddress().getPostcode(), account.getAddress().getCountry(), account.getEmailAddress(), account.getUsername(), account.getPassword(), account.getTelephoneNumber(), account.getSecurityQuestion().getSecurityQuestionID(), securityAnswer.getAnswer(), account.getSecurityPIN());

    }

    public String updateAccountSettings(Long accountId, AccountDTO accountDTO) {

        Account account = accountRepository.findByAccountID(accountId);

        if (account == null) {

            return "There is no account that matches the provided account ID. Please try again.";

        }

        SecurityAnswer securityAnswer = securityAnswerRepository.findByAccount(account);

        if (accountDTO.getFullName() != null && !accountDTO.getFullName().isEmpty()) {

            account.setFullName(accountDTO.getFullName());

        }

        if (accountDTO.getGender() != null && !accountDTO.getGender().isEmpty()) {

            account.setGender(accountDTO.getGender());

        }

        if (accountDTO.getDateOfBirth() != null) {

            account.setDateOfBirth(accountDTO.getDateOfBirth());

        }

        if (accountDTO.getStreetName1() != null && !accountDTO.getStreetName1().isEmpty()) {

            account.getAddress().setStreetName1(accountDTO.getStreetName1());

        }

        if (accountDTO.getStreetName2() != null && !accountDTO.getStreetName2().isEmpty()) {

            account.getAddress().setStreetName2(accountDTO.getStreetName2());

        }

        if (accountDTO.getTown() != null && !accountDTO.getTown().isEmpty()) {

            account.getAddress().setTown(accountDTO.getTown());

        }

        if (accountDTO.getState() != null && !accountDTO.getState().isEmpty()) {

            account.getAddress().setState(accountDTO.getState());

        }

        if (accountDTO.getPostcode() != null && !accountDTO.getPostcode().isEmpty()) {

            account.getAddress().setPostcode(accountDTO.getPostcode());

        }

        if (accountDTO.getCountry() != null && !accountDTO.getCountry().isEmpty()) {

            account.getAddress().setCountry(accountDTO.getCountry());

        }

        if (accountDTO.getEmailAddress() != null && !accountDTO.getEmailAddress().isEmpty()) {

            account.setEmailAddress(accountDTO.getEmailAddress());

        }

        if (accountDTO.getUsername() != null && !accountDTO.getUsername().isEmpty()) {

            account.setUsername(accountDTO.getUsername());

        }

        if (accountDTO.getPassword() != null && !accountDTO.getPassword().isEmpty()) {

            account.setPassword(passwordEncoder.encode(accountDTO.getPassword()));

        }

        if (accountDTO.getTelephoneNumber() != null && !accountDTO.getTelephoneNumber().isEmpty()) {

            account.setTelephoneNumber(accountDTO.getTelephoneNumber());

        }

        if (accountDTO.getSecurityQuestionID() != null) {

            account.setSecurityQuestion(securityQuestionRepository.findBySecurityQuestionID(accountDTO.getSecurityQuestionID()));

        }

        if (accountDTO.getSecurityAnswer() != null && !accountDTO.getSecurityAnswer().isEmpty()) {

            securityAnswer.setAnswer(accountDTO.getSecurityAnswer());

            securityAnswerRepository.save(securityAnswer);

        }

        if (accountDTO.getSecurityPIN() != null && !accountDTO.getSecurityPIN().isEmpty()) {

            account.setSecurityPIN(passwordEncoder.encode(accountDTO.getSecurityPIN()));

        }

        accountRepository.save(account);

        return "Account settings updated successfully.";

    }

    public Long getAccountCount() {

        return (long) accountRepository.findAll().size();

    }

    public String getUsername(Long accountId) {

        return accountRepository.findByAccountID(accountId).getUsername();

    }

    // Other service methods

}