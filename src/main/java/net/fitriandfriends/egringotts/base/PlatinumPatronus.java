package net.fitriandfriends.egringotts.base;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PLATINUM_PATRONUS")
public class PlatinumPatronus extends User {

    public PlatinumPatronus() {

        super("PLATINUM_PATRONUS");

    }

}