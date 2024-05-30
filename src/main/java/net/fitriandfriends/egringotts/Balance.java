package net.fitriandfriends.egringotts;

import jakarta.persistence.*;

@Entity
@Table
public class Balance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long balanceID;

    @ManyToOne
    @JoinColumn(name = "accountID", referencedColumnName = "accountID")
    private Account account;

    private String currency;
    private Double balance;

    public Balance() {
    }

    public Balance(Long balanceID, Account account, String currency, Double balance) {
        this.balanceID = balanceID;
        this.account = account;
        this.currency = currency;
        this.balance = balance;
    }

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
}
