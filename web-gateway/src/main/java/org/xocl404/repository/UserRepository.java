package org.xocl404.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.xocl404.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    @Modifying
    @Query("DELETE FROM User u WHERE u.ownerId IS NOT NULL")
    void deleteAllByOwnerIdIsNotNull();

    @Modifying
    @Query("DELETE FROM User u WHERE u.ownerId = :ownerId")
    void deleteByOwnerId(@Param("ownerId") Long ownerId);
}
