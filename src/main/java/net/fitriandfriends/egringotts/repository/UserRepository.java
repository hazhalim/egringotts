package net.fitriandfriends.egringotts.repository;

import net.fitriandfriends.egringotts.base.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository<T extends User> extends JpaRepository<T, Long> {

    // Other queries

}