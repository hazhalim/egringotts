package net.fitriandfriends.egringotts;

import jakarta.persistence.*;

@Entity
@Table
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public abstract class User {

    // Instance variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;

    String type;

    // Constructors
    public User() {}

    public User(String type) {

        this.type = type;

    }

    // Accessor and mutator methods

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
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