package com.server.diary.service;

import com.server.diary.common.authorizations.TokenManager;
import com.server.diary.common.exception.Exceptions;
import com.server.diary.common.exception.ServiceException;
import com.server.diary.common.mapper.Mapper;
import com.server.diary.repository.dto.UserDto;
import com.server.diary.repository.userRepository.User;
import com.server.diary.repository.userRepository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper mapper;
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
    @Transactional
    public UserDto getUser(UserDto userDto) throws ServiceException{
        User user = userRepository.getUserByUserId(userDto.getUserId())
                .orElseThrow(() ->  new ServiceException(Exceptions.NO_DATA));
        String password = userDto.getPassword();
        String rawPassword = user.getPassword();
        log.warn("?? {}", passwordEncoder.matches(rawPassword, password));
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
    public UserDto saveUser(UserDto userDto) throws ServiceException {
        Optional<User> userRef = userRepository.getUserByUserId(userDto.getUserId());


        if(!userRef.isEmpty()) throw new ServiceException(Exceptions.ALREADY_EXIST);

        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user = Mapper.modelMapping(userDto, new User());
        log.warn("USERDTO {}", userDto);
        log.warn("USER {}", user);
        user = userRepository.save(user);
//        user.setRefreshToken(TokenManager.refreshEncrypt(user.getUserNo()));
        userDto = mapper.map(user, UserDto.class);
        return userDto;
    }
}
