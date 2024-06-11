package net.fitriandfriends.egringotts.base;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("GOLDEN_GALLEON")
public class GoldenGalleon extends User {

    public GoldenGalleon() {

        super("GOLDEN_GALLEON");

    }

}