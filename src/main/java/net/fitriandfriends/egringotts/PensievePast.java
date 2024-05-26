package net.fitriandfriends.egringotts;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PensievePast {

    // Instance variable
    private Stack<Transaction> transactionStack;

    // Constructor
    public PensievePast() {

        transactionStack = new Stack<>();

    }

    // Accessor and mutator methods
    public Stack<Transaction> getTransactionStack() {

        return this.transactionStack;

    }

    public void setTransactionStack(Stack<Transaction> transactionStack) {

        this.transactionStack= transactionStack;

    }

    // Other methods
    public void pushTransactionsToStack(List<Transaction> transactionList) {

        for (Transaction transaction : transactionList) {

            transactionStack.push(transaction);

        }

    }

    public List<Transaction> getPensievePast() {

        Stack<Transaction> tempStack = new Stack<>();
        List<Transaction> pensievePast = new ArrayList<>();

        while (!transactionStack.isEmpty()) {

            Transaction transaction = transactionStack.pop();

            pensievePast.add(transaction);
            tempStack.push(transaction);

        }

        while (!tempStack.isEmpty()) {

            transactionStack.push(tempStack.pop());

        }

        return pensievePast;

    }

}