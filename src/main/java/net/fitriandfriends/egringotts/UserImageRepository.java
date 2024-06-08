package net.fitriandfriends.egringotts;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserImageRepository extends JpaRepository<UserImage, Long> {

    public UserImage findByUserImageID(Long userImageID);

}