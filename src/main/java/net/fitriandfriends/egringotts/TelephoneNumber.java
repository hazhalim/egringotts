package net.fitriandfriends.egringotts;

import jakarta.persistence.Embeddable;

@Embeddable
public class TelephoneNumber {

    // Instance variables
    private String type;

    private int countryCode;
    private String countryName;

    private String areaCode;
    private String areaName;

    private String subscriberNumber;

    // Constructor

    public TelephoneNumber() {}

    public TelephoneNumber(String type, int countryCode, String countryName, String areaCode, String areaName, String subscriberNumber) {

        this.type = type;
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.areaCode = areaCode;
        this.areaName = areaName;
        this.subscriberNumber = subscriberNumber;

    }

    // Accessor and mutator methods
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(int countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getSubscriberNumber() {
        return subscriberNumber;
    }

    public void setSubscriberNumber(String subscriberNumber) {
        this.subscriberNumber = subscriberNumber;
    }

    // Other methods
    @Override
    public String toString() {
        return "TelephoneNumber{" +
                "type='" + type + '\'' +
                ", countryCode=" + countryCode +
                ", countryName='" + countryName + '\'' +
                ", areaCode='" + areaCode + '\'' +
                ", areaName='" + areaName + '\'' +
                ", subscriberNumber='" + subscriberNumber + '\'' +
                '}';
    }

    public String getTelephoneNumber() {

        return "+" + countryCode + areaCode + subscriberNumber;

    }

}