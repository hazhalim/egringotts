package net.fitriandfriends.egringotts;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@DiscriminatorValue("Golden Galleon")
public class GoldenGalleon extends User {

    public GoldenGalleon() {

        super("Golden Galleon");

    }

}