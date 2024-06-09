package net.fitriandfriends.egringotts.base;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Golden Galleon")
public class GoldenGalleon extends User {

    public GoldenGalleon() {

        super("Golden Galleon");

    }

}