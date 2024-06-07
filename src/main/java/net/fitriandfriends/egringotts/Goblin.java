package net.fitriandfriends.egringotts;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@DiscriminatorValue("Goblin")
public class Goblin extends User {

    // Constructor
    public Goblin() {

        super("Goblin");

    }

}