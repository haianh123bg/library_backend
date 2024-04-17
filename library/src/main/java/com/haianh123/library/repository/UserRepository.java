package com.haianh123.library.repository;

import com.haianh123.library.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByUserAccountName(String userAccountName);
}
