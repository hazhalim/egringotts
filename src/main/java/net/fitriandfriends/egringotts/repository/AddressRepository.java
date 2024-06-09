package net.fitriandfriends.egringotts.repository;

import net.fitriandfriends.egringotts.base.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

    public List<Address> findAll();
    public Address findByAddressID(Long addressID);

}