package net.fitriandfriends.egringotts;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Silver Snitch")
public class SilverSnitch extends User {

    public SilverSnitch() {

        super("Silver Snitch");

    }

}