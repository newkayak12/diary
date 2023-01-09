package com.server.diary.userTest;

import com.server.diary.baseTest.BaseTest;
import com.server.diary.repository.dto.UserDto;
import org.junit.Test;

public class UserTest extends BaseTest {

    @Test
    public void signUp(){
        UserDto userDto = UserDto.builder().userId("test").password("1212").build();
        System.out.println(userDto);
        this.post("/user/up", userDto);
    }

    @Test
    public void signIn(){
        UserDto userDto = UserDto.builder().userId("test").password("1212").build();
        System.out.println(userDto);
        this.get("/user/in", userDto);
    }
}
