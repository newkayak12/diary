package com.server.diary.repository.photoRepository;

import com.server.diary.repository.memoryRepository.Memory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
}
