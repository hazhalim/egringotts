package net.fitriandfriends.egringotts.base;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table
@Data
@Getter
@Setter
@ToString
public class Balance {

    // Instance variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "balance_id")
    private Long balanceID;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    private Account account;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "currency_id", referencedColumnName = "currency_id")
    private Currency currency;

    @Column(name = "balance")
    private Double balance;

    // Constructors
    public Balance() {}

    public Balance(Account account, Currency currency, Double balance) {
        this.account = account;
        this.currency = currency;
        this.balance = balance;
    }

    // Accessor, mutator, and toString methods are handled by Lombok

}