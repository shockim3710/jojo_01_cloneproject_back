package com.cloneproject.ssgjojo.user.service;

import com.cloneproject.ssgjojo.user.domain.User;
import com.cloneproject.ssgjojo.user.dto.*;
import com.cloneproject.ssgjojo.user.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class IUserServiceImple implements IUserService{

    private final IUserRepository iUserRepository;

    @Override
    public User addUser(UserSignupDto userSignupDto) { // 회원가입
//        log.info("{} added", user);

        userSignupDto.setIsLeave(false);
        userSignupDto.setMembershipLevel("Friends");

        return iUserRepository.save(User.builder()
                .userId(userSignupDto.getUserId())
                .password(userSignupDto.getPassword())
                .name(userSignupDto.getName())
                .birth(userSignupDto.getBirth())
                .phone(userSignupDto.getPhone())
                .email(userSignupDto.getEmail())
                .gender(userSignupDto.getGender())
                .membershipLevel(userSignupDto.getMembershipLevel())
                .isLeave(userSignupDto.getIsLeave())
                .build());
    }

    @Override
    public UserGetIdDto getUserById(Long id) { // 마이페이지

        User user = iUserRepository.findById(id).get();
        return UserGetIdDto.builder()
                .id(user.getId())
                .userId(user.getUserId())
                .password(user.getPassword())
                .name(user.getName())
                .birth(user.getBirth())
                .phone(user.getPhone())
                .email(user.getEmail())
                .gender(user.getGender())
                .membershipLevel(user.getMembershipLevel())
                .build();
    }



    @Override
    public List<UserGetAllDto> getAll() { // 회원 조회
        List<User> userList = iUserRepository.findAll();
        List<UserGetAllDto> userDtoUserGetAllDtoList = new ArrayList<>();

        userList.forEach( user -> {
            userDtoUserGetAllDtoList.add(UserGetAllDto
                    .builder()
                    .userId(user.getUserId())
                            .id(user.getId())
                            .birth(user.getBirth())
                            .gender(user.getGender())
                            .email(user.getEmail())
                            .name(user.getName())
                            .password(user.getPassword())
                            .phone(user.getPhone())
                            .membershipLevel(user.getMembershipLevel())
                            .isLeave(user.getIsLeave())
                    .build());
                }
        );

        return userDtoUserGetAllDtoList;
    }

    @Override
    public User editUser(UserEditDto userEditDto) { // 회원 정보 수정
        return iUserRepository.save(User.builder()
                        .id(userEditDto.getId())
                        .password(userEditDto.getPassword())
                        .birth(userEditDto.getBirth())
                        .phone(userEditDto.getPhone())
                        .email(userEditDto.getEmail())
                        .gender(userEditDto.getGender())
                .build());
    }



    @Override
    public User deleteUser(UserDeleteDto userDeleteDto) { // 회원 탈퇴

        userDeleteDto.setIsLeave(true);

        return iUserRepository.save(User.builder()
                .id(userDeleteDto.getId())
                .isLeave(userDeleteDto.getIsLeave())
                .build());
    }
}
