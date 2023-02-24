package com.server.diary.memoryTest;

import com.server.diary.common.authorizations.annotations.AuthorizeDto;
import com.server.diary.common.exception.ServiceException;
import com.server.diary.repository.dto.MemoryDto;
import com.server.diary.repository.dto.PhotoDto;
import com.server.diary.repository.dto.SearchParameter;
import com.server.diary.repository.dto.UserDto;
import com.server.diary.service.MemoryService;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
@Profile(value = "local")
public class MemoryTest {

    @Autowired
    public MemoryService memoryService;
    public MultipartFile multipartTest() throws IOException{
        String path = "/Users/sanghyeonkim/Downloads/port/DIARY/src/main/resources/img.jpg";
        FileInputStream fileInputStream = new FileInputStream(path);
        MockMultipartFile file = new MockMultipartFile("img", String.format("%s.%s", "img", "jpg"), "png", fileInputStream);
        return file;
    }

    @Test
    @DisplayName(value = "저장/ 수정 테스트")
    public void saveTest() throws IOException, ServiceException {
        MemoryDto memoryDto = MemoryDto.builder().user(UserDto.builder().userNo(18L).build())
//                           .memoryNo(4L)
//                           .firstPhoto(PhotoDto.builder().photoNo(25L).build())
//                           .secondPhoto(PhotoDto.builder().photoNo(26L).build())
//                           .thirdPhoto(PhotoDto.builder().photoNo(27L).build())
                           .firstMultipartFile(multipartTest())
                           .secondMultipartFile(multipartTest())
                           .thirdMultipartFile(multipartTest())
                           .contents("TEST3")
                           .address("TEST3")
                           .build();
       memoryDto.setCategory("CULTURAL_LIFE");
       memoryService.save(memoryDto);
    }
    @Test
    @DisplayName(value = "삭제 테스트")
    public void removeTest() {
        Assertions.assertThat(memoryService.remove(2L)).isEqualTo("삭제했습니다.");
    }

    @Test
    @DisplayName(value = "단일 조희 테스트")
    public void fetchMemoryTest(){
        System.out.println(memoryService.fetchMemory(3L));
    }

    @Test
    @DisplayName(value = "리스트 조회 테스트")
    public  void fetchMemoriesTest(){
        SearchParameter searchParameter = new SearchParameter();
        searchParameter.setUserNo(18L);
        searchParameter.setPage(1);
        searchParameter.setLimit(1);
        System.out.println(memoryService.fetchMemoryList(searchParameter));
    }

}
