package net.fitriandfriends.egringotts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private SecurityQuestionRepository securityQuestionRepository;
    @Autowired
    private BalanceService balanceService;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserImageRepository userImageRepository;
    @Autowired
    private UserRepository userRepository;

    @CacheEvict(value = {"accountsSortedAscending", "accountsSortedDescending", "accountsByFullName", "accountsByTelephoneNumber"}, allEntries = true)
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

        // Create a new UserImage object based on details provided in accountDTO
        UserImage userImage = new UserImage(accountDTO.getUserImageURL());
        userImageRepository.save(userImage); // Save the new user image to the database

        // Attach the SecurityQuestion to the account based on the securityQuestionID provided in accountDTO
        SecurityQuestion securityQuestion = securityQuestionRepository.findBySecurityQuestionID(accountDTO.getSecurityQuestionID());
        securityQuestionRepository.save(securityQuestion); // Save the new security question to the database

        // Create a new Account object based on details provided in accountDTO and the new objects created above
        Account account = new Account(newUser, accountDTO.getFullName(), accountDTO.getGender(), accountDTO.getDateOfBirth(), newAddress, userImage, accountDTO.getEmailAddress(), accountDTO.getUsername(), accountDTO.getPassword(), accountDTO.getTelephoneNumber(), securityQuestion, accountDTO.getSecurityPIN());

        // Save the new account to the database
        return accountRepository.save(account);

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

//    public List<Account> searchAccountByFullName(String fullName) {
//
//        List<Account> sortedAccounts = getAllAccountsSortedAscending("fullName");
//
//        List<Account> matchingAccounts = new ArrayList<>();
//
//        int index = Collections.binarySearch(sortedAccounts, new Account(fullName, true), new FullNameComparator());
//
//        if (index >= 0) {
//
//            matchingAccounts.add(sortedAccounts.get(index));
//
//            return matchingAccounts;
//
//        } else {
//
//            return null;
//
//        }
//
//    }

    // Search for any account by telephone number
    @Cacheable("accountsByTelephoneNumber")
    public List<Account> searchMatchingAccountsByTelephoneNumber(String telephoneNumber) {

        return accountRepository.findByTelephoneNumberContaining(telephoneNumber);

    }

//    public List<Account> searchAccountByTelephoneNumber(String telephoneNumber) {
//
//        List<Account> sortedAccounts = getAllAccountsSortedAscending("telephoneNumber");
//
//        List<Account> matchingAccounts = new ArrayList<>();
//
//        int index = Collections.binarySearch(sortedAccounts, new Account(telephoneNumber), new TelephoneNumberComparator());
//
//        if (index >= 0) {
//
//            matchingAccounts.add(sortedAccounts.get(index));
//
//            return matchingAccounts;
//
//        } else {
//
//            return null;
//
//        }
//
//    }

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

//    public List<Account> searchAccount(String fullName, String telephoneNumber) {
//
//        List<Account> sortedAccounts = getAllAccountsSortedAscending("fullName");
//
//        List<Account> matchingAccounts = new ArrayList<>();
//
//        int index = Collections.binarySearch(sortedAccounts, new Account(fullName, true), new FullNameComparator());
//
//        if (index >= 0) {
//
//            matchingAccounts.add(sortedAccounts.get(index));
//
//            return matchingAccounts;
//
//        } else { // If not found by fullName, try to find by telephoneNumber instead
//
//            sortedAccounts = getAllAccountsSortedAscending("telephoneNumber");
//
//            index = Collections.binarySearch(sortedAccounts, new Account(telephoneNumber), new TelephoneNumberComparator());
//
//            if (index >= 0) {
//
//                matchingAccounts.add(sortedAccounts.get(index));
//
//                return matchingAccounts;
//
//            } else {
//
//                return matchingAccounts; // This list will be empty, however
//
//            }
//
//        }
//
//    }

    // Other service methods

}