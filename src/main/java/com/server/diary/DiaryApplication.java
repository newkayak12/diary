package com.server.diary;

import com.server.diary.common.apiInspectionGenerator.ApiInspectionGenerator;
import com.server.diary.common.constants.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@Slf4j
public class DiaryApplication {
    public static void main(String[] args) {
        SpringApplication.run(DiaryApplication.class, args);

        System.out.println(Constants.FILE_MAXIMUM_SIZE);
    }
}
