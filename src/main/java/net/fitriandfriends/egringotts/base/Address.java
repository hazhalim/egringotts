package net.fitriandfriends.egringotts.base;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Data
@Getter
@Setter
public class Address {

    // Instance variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long addressID;

    @Column(name = "street_name_1")
    private String streetName1;

    @Column(name = "street_name_2")
    private String streetName2;

    @Column(name = "town")
    private String town;

    @Column(name = "state")
    private String state;

    @Column(name = "postcode")
    private String postcode;

    @Column(name = "country")
    private String country;

    // Constructors
    public Address() {}

    // With street name 2
    public Address(String streetName1, String streetName2, String town, String state, String postcode, String country) {

        this.streetName1 = streetName1;
        this.streetName2 = streetName2;
        this.town = town;
        this.state = state;
        this.postcode = postcode;
        this.country = country;

    }

    // Without street name 2
    public Address(String streetName1, String town, String state, String postcode, String country) {

        this.streetName1 = streetName1;
        this.streetName2 = null;
        this.town = town;
        this.state = state;
        this.postcode = postcode;
        this.country = country;

    }

    // Accessor and mutator methods are handled by Lombok

    // Other methods
    @Override
    public String toString() {

        if (this.streetName2 != null && !this.streetName2.isEmpty()) {

            return "Address: " + this.streetName1 + ", " + this.streetName2 + ", " + this.postcode + ", " + this.town + ", " + this.state + ", " + this.country;

        } else {

            return "Address: " + this.streetName1 + ", " + this.postcode + ", " + this.town + ", " + this.state + ", " + this.country;

        }

    }

}