package net.fitriandfriends.egringotts.repository;

import net.fitriandfriends.egringotts.base.UserImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserImageRepository extends JpaRepository<UserImage, Long> {

    public UserImage findByUserImageID(Long userImageID);

}