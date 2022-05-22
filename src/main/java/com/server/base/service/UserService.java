package com.server.base.service;

import com.server.base.common.exception.Exceptions;
import com.server.base.common.exception.ServiceException;
import com.server.base.common.mapper.Mapper;
import com.server.base.repository.dto.UserDto;
import com.server.base.repository.entity.User;
import com.server.base.repository.userRepository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public String getRefreshToken(Long userNo){
        return userRepository.getUserByUserNo(userNo).getAuthEntity().getRefreshToken();
    }
    public UserDto getUser(UserDto userDto) throws ServiceException{
        User user = userRepository.getUserByUserId(userDto.getId())
                .orElseThrow(() ->  new ServiceException(Exceptions.NO_DATA));
        String password = userDto.getPassword();
        String rawPassword = user.getPassword();
        if(!passwordEncoder.matches(password, rawPassword)){
            throw  new ServiceException(Exceptions.NO_DATA);
        }
        return Mapper.modelMapping(user, new UserDto());
    }


}
