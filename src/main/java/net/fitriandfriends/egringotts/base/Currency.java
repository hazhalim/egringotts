package net.fitriandfriends.egringotts.base;

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
public class Currency implements Comparable<Currency> {

    // Instance variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "currency_id")
    private Long currencyID;

    @Column(name = "name")
    private String name;

    @Column(name = "abbreviation")
    private String abbreviation;

    // Constructors
    public Currency() {}

    public Currency(String name, String abbreviation) {

        this.name = name;
        this.abbreviation = abbreviation;

    }

    // Accessor, mutator, and toString methods are handled by Lombok

    // Other methods
    @Override
    public int compareTo(Currency otherCurrency) {

        return this.abbreviation.compareTo(otherCurrency.abbreviation);

    }

}
