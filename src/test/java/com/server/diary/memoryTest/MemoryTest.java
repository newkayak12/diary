package com.server.diary.memoryTest;

import com.server.diary.common.fileUpload.FileUpload;
import org.junit.Test;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mock.web.MockMultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MemoryTest {
    @Test
    public void multipartTest() throws IOException, FileUpload.FileException {
        String path = "/Users/sanghyeonkim/Downloads/port/DIARY/src/main/resources/img.jpg";
        FileInputStream fileInputStream = new FileInputStream(path);
        MockMultipartFile file = new MockMultipartFile("img", String.format("%s.%s", "img", "jpg"), "png", fileInputStream);

        FileUpload fileUpload = new FileUpload("/Users/sanghyeonkim/Downloads/port/DIARY/src/main/resources/multi",null, true, 1024L * 10 );
        fileUpload.upload(true, file);

    }
}
