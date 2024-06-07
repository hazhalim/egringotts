package net.fitriandfriends.egringotts;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    // Find all accounts, and sort the accounts by a certain criteria (i.e. full name, telephone number)
    public List<Account> findAll(Sort sort);

    // Find an account by username
    public Account findByUsername(String username);

    // Find accounts by full name containing a search term
    List<Account> findByFullNameContainingIgnoreCase(String fullName);

    // Find accounts by telephone number containing a search term
    List<Account> findByTelephoneNumberContaining(String telephoneNumber);

    // Other queries

}