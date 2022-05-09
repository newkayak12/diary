package com.server.base.repository.testRepository;

import com.server.base.repository.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<Test, Long>, TestRepositoryCustom{
}
