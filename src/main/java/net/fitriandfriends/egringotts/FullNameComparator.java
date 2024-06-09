package net.fitriandfriends.egringotts;

import lombok.Data;
import net.fitriandfriends.egringotts.base.Account;

import java.util.Comparator;

@Data
public class FullNameComparator implements Comparator<Account> {

    // No instance variables
    // No constructors
    // No accessor and mutator methods

    // Other methods
    // Compare the full names of two accounts
    @Override
    public int compare(Account firstAccount, Account secondAccount) {

        return firstAccount.getFullName().compareTo(secondAccount.getFullName());

    }

}