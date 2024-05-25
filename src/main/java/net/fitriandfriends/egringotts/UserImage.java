package net.fitriandfriends.egringotts;

import jakarta.persistence.*;

@Entity
public class UserImage {

    // Instance variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    // Other methods

}