package net.fitriandfriends.egringotts;

import jakarta.persistence.*;

@Entity
@Table
public class UserImage {

    // Instance variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userImageID;

    @JoinColumn(name = "account_id")
    private Long accountId;

    private String imagePath;

    // Constructors
    public UserImage() {}

    public UserImage(Long accountId, String imagePath) {

        this.accountId = accountId;
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

}