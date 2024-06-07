package net.fitriandfriends.egringotts;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table
@Data
@Getter
@Setter
@ToString
public class Currency {

    // Instance variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "currency_id")
    private Long currencyID;

    @Column(name = "name")
    private String name;

    // Constructors
    public Currency() {}

    public Currency(String name) {

        this.name = name;

    }

    // Accessor, mutator, and toString methods are handled by Lombok

    // Other methods

}
