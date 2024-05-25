package net.fitriandfriends.egringotts;

import jakarta.persistence.*;

@Entity
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
public abstract class User {

    // Instance variables
    @Id
    private Long accountId;

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(nullable = false)
    String type;

    // Constructors
    public User() {}

    public User(String type) {

        this.type = type;

    }

    // Accessor and mutator methods
    public Long getAccountId() {

        return this.accountId;

    }

    public void setAccountId(Long accountId) {

        if (type.equals("Goblin")) {

            this.accountId = accountId;

        }

    }

    public Account getAccount() {

        return this.account;

    }

    public void setAccount(Account account) {

        if (type.equals("Goblin")) {

            this.account = account;

        }

    }

    public String getType() {

        return this.type;

    }

    public void setType(String type) {

        if (type.equals("Goblin")) {

            this.type = type;

        }

    }

}