package net.fitriandfriends.egringotts;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@DiscriminatorValue("Platinum Patronus")
public class PlatinumPatronus extends User {

    public PlatinumPatronus() {

        super("Platinum Patronus");

    }

}