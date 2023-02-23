package com.server.diary.memoryTest;
import org.junit.Test;
import org.newkayak.FileUpload.FileResult;
import org.newkayak.FileUpload.FileUpload;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mock.web.MockMultipartFile;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class MemoryTest {
    @Test
    public void multipartTest() throws IOException{
        String path = "/Users/sanghyeonkim/Downloads/port/DIARY/src/main/resources/img.jpg";
        FileInputStream fileInputStream = new FileInputStream(path);
        MockMultipartFile file = new MockMultipartFile("img", String.format("%s.%s", "img", "jpg"), "png", fileInputStream);

        FileUpload fileUpload = new FileUpload("/Users/sanghyeonkim/Downloads/port/DIARY/src/main/resources/multi",true, 1024L * 100 );
        List<FileResult> list = fileUpload.upload(true, file);
        System.out.println(list);

    }

    @Test
    public void test() {
        CompletableFuture.supplyAsync(() -> 1)
        .thenApply((value) -> {
            System.out.println(value);
            return value;
        });
    }
}
