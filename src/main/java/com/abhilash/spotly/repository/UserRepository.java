package com.abhilash.spotly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.abhilash.spotly.entity.User;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

}