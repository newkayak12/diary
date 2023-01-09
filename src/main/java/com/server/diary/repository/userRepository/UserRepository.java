package com.server.diary.repository.userRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
    User getUserByUserNo(Long userNo);
    Optional<User> getUserByUserId(String userId);

}
