package net.fitriandfriends.egringotts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository<T extends User> extends JpaRepository<T, Long> {

    // Query methods

}