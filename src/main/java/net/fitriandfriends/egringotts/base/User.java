package net.fitriandfriends.egringotts.base;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@DiscriminatorColumn(name = "roleType", discriminatorType = DiscriminatorType.STRING)
@Data
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

}