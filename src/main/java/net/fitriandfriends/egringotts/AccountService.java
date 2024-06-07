package net.fitriandfriends.egringotts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account createAccount(Account account) {

        return accountRepository.save(account);

    }

    // Get all accounts sorted by a certain criteria in ascending order
    public List<Account> getAllAccountsSortedAscending(String sortBy) {

        Sort sort = Sort.by(sortBy).ascending();

        return accountRepository.findAll(sort);

    }

    // Get all accounts sorted by a certain criteria in descending order
    public List<Account> getAllAccountsSortedDescending(String sortBy) {

        Sort sort = Sort.by(sortBy).descending();

        return accountRepository.findAll(sort);

    }

    // Search for any account by full name
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