package com.server.diary;

import com.server.diary.common.apiInspectionGenerator.ApiInspectionGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@Slf4j
public class DiaryApplication {
    public static void main(String[] args) {

        ApiInspectionGenerator.inspection();
        SpringApplication.run(DiaryApplication.class, args);
    }
}
