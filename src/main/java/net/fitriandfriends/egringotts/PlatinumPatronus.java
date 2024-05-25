package net.fitriandfriends.egringotts;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Platinum Patronus")
public class PlatinumPatronus extends User {

    public PlatinumPatronus() {

        super("Platinum Patronus");

    }

}