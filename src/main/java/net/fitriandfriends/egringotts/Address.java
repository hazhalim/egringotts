package net.fitriandfriends.egringotts;

import jakarta.persistence.*;

@Entity
@Table
public class Address {

    // Instance variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressID;
    private String type;
    private String streetName1;
    private String streetName2;
    private String town;
    private String state;
    private String postcode;
    private String country;

    // Constructor
    public Address() {}

    public Address(String streetName1, String streetName2, String town, String state, String postcode, String country) {

        this.streetName1 = streetName1;
        this.streetName2 = streetName2;
        this.town = town;
        this.state = state;
        this.postcode = postcode;
        this.country = country;

    }

    // Accessor and mutator methods
    public String getType() {

        return this.type;

    }

    public void setType(String type) {

        this.type = type;

    }

    public String getStreetName1() {

        return this.streetName1;

    }

    public void setStreetName1(String streetName1) {

        this.streetName1 = streetName1;

    }

    public String getStreetName2() {

        return this.streetName2;

    }

    public void setStreetName2(String streetName2) {

        this.streetName2 = streetName2;

    }

    public String getTown() {

        return this.town;

    }

    public void setTown(String town) {

        this.town = town;

    }

    public String getState() {

        return state;

    }

    public void setState(String state) {

        this.state = state;

    }

    public String getPostcode() {

        return this.postcode;

    }

    public void setPostcode(String postcode) {

        this.postcode = postcode;

    }

    public String getCountry() {

        return this.country;

    }

    public void setCountry(String country) {

        this.country = country;

    }

    // Other methods
    @Override
    public String toString() {

        if (this.streetName2 != null && !this.streetName2.isEmpty()) {

            return "Address Type: " + this.type + " Address: " + this.streetName1 + ", " + this.streetName2 + ", " + this.postcode + ", " + this.town + ", " + this.state + ", " + this.country;

        } else {

            return "Address Type: " + this.type + " Address: " + this.streetName1 + ", " + this.postcode + ", " + this.town + ", " + this.state + ", " + this.country;

        }

    }

}