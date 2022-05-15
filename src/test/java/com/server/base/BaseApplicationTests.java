package com.server.base;

import com.server.base.common.authorizations.TokenManager;
import com.server.base.controller.TestController;
import com.server.base.repository.dto.TestDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class BaseApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void aopTest(){

        TestDto v = TestDto.builder().id1("1").id2(1).id3(new Long(3)).id4(LocalDateTime.now()).build();
        TokenManager tk = new TokenManager();
        String token = tk.encrypt(v);

        TestController t = new TestController();
        t.tst(token);
    }

}
