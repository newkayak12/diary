package com.server.base.service;

import com.server.base.common.authorizations.TokenManager;
import com.server.base.common.exception.Exceptions;
import com.server.base.common.exception.ServiceException;
import com.server.base.common.mapper.Mapper;
import com.server.base.repository.dto.UserDto;
import com.server.base.repository.userRepository.User;
import com.server.base.repository.userRepository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Refresh 토큰 가져오기
     * @param userNo
     * @return
     */
    @Transactional(readOnly = true)
    public UserDto getRefreshToken(Long userNo){
        return Mapper.modelMapping(userRepository.getUserByUserNo(userNo), new UserDto());
    }

    /**
     * 사용자 가져오기
     * @param userDto
     * @return
     * @throws ServiceException
     */
    @Transactional(readOnly = true)
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

    /**
     * 사용자 등록
     * @param userDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public UserDto saveUser(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user = Mapper.modelMapping(userDto, new User());
        user = userRepository.save(user);
        user.setRefreshToken(TokenManager.refreshEncrypt(user.getUserNo()));
        userDto = Mapper.modelMapping(user, userDto);
        return userDto;
    }
}
