package net.fitriandfriends.egringotts;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

@Setter
@Getter
@Data
@ToString
public class PensievePast {

    // Accessor and mutator methods
    // Instance variable
    private Stack<Transaction> transactionStack;

    // Constructor
    public PensievePast() {

        transactionStack = new Stack<>();

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