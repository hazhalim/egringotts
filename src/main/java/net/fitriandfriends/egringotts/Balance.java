package net.fitriandfriends.egringotts;

import jakarta.persistence.*;

@Entity
@Table
public class Balance {

    // Instance variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "balance_id")
    private Long balanceID;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    private Account account;

    @Column(name = "currency")
    private String currency;

    @Column(name = "balance")
    private Double balance;

    // Constructors
    public Balance() {}

    public Balance(Account account, String currency, Double balance) {
        this.account = account;
        this.currency = currency;
        this.balance = balance;
    }

    // Accessor and mutator methods
    public Long getBalanceID() {
        return balanceID;
    }

    public void setBalanceID(Long balanceID) {
        this.balanceID = balanceID;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    // Other methods
    @Override
    public String toString() {
        return "Balance{" +
                "balanceID=" + balanceID +
                ", account=" + account +
                ", currency='" + currency + '\'' +
                ", balance=" + balance +
                '}';
    }

}