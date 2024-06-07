package net.fitriandfriends.egringotts;

import java.util.Comparator;

public class TelephoneNumberComparator implements Comparator<Account> {

    // No instance variables
    // No constructors
    // No accessor and mutator methods

    // Other methods
    // Compare the telephone numbers of two accounts
    @Override
    public int compare(Account firstAccount, Account secondAccount) {

        return firstAccount.getTelephoneNumber().compareTo(secondAccount.getTelephoneNumber());

    }

}