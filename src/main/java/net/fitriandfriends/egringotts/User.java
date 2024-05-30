package net.fitriandfriends.egringotts;

import jakarta.persistence.*;

@Entity
@Table
@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.STRING)
public abstract class User {

    // Instance variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userID;

    @Column(name = "type")
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

    // Other methods
    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", type='" + type + '\'' +
                '}';
    }

}