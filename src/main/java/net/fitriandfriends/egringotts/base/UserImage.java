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
public class UserImage {

    // Instance variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_image_id")
    private Long userImageID;

    @Column(name = "image_path")
    private String imagePath;

    // Constructors
    public UserImage() {}

    public UserImage(String imagePath) {

        this.imagePath = imagePath;

    }

    // Accessor, mutator, and toString methods are handled by Lombok

}