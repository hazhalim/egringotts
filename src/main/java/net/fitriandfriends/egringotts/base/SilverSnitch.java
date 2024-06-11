package net.fitriandfriends.egringotts.base;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("SILVER_SNITCH")
public class SilverSnitch extends User {

    public SilverSnitch() {

        super("SILVER_SNITCH");

    }

}