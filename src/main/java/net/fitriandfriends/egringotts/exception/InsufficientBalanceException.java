package net.fitriandfriends.egringotts.exception;

// An exception caused during a transaction when the sender account has insufficient balance
public class InsufficientBalanceException extends Exception {

    public InsufficientBalanceException(String message) {

        super(message);

    }

}