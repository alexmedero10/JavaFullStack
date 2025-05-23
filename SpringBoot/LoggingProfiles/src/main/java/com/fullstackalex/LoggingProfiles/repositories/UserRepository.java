package com.fullstackalex.LoggingProfiles.repositories;

import com.fullstackalex.LoggingProfiles.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}