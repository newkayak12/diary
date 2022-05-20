package com.server.base.repository.userRepository;

import com.server.base.repository.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Test, Long>, UserRepositoryCustom {
}
