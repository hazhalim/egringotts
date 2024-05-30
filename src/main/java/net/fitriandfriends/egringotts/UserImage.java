package net.fitriandfriends.egringotts;

import jakarta.persistence.*;

@Entity
@Table
public class UserImage {

    // Instance variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userImageID")
    private Long userImageID;

    @Column(name = "imagePath")
    private String imagePath;

    // Constructors
    public UserImage() {}

    public UserImage(String imagePath) {

        this.imagePath = imagePath;

    }

    // Accessor and mutator methods
    public Long getUserImageID() {
        return userImageID;
    }

    public void setUserImageID(Long id) {
        this.userImageID = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    // Other methods
    @Override
    public String toString() {
        return "UserImage{" +
                "userImageID=" + userImageID +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }

}