package net.fitriandfriends.egringotts.base;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Goblin")
public class Goblin extends User {

    // Constructor
    public Goblin() {

        super("Goblin");

    }

}