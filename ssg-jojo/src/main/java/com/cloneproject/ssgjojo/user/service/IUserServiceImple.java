package com.cloneproject.ssgjojo.user.service;

import com.cloneproject.ssgjojo.user.domain.User;
import com.cloneproject.ssgjojo.user.dto.*;
import com.cloneproject.ssgjojo.user.repository.IUserRepository;
import com.cloneproject.ssgjojo.util.BaseTimeEntity;
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
    public User addUser(UseSignupDto useSignupDto) { // 회원가입
//        log.info("{} added", user);

        useSignupDto.setIsLeave(false);
        useSignupDto.setMembershipLevel("Friends");

        return iUserRepository.save(User.builder()
                .userId(useSignupDto.getUserId())
                .password(useSignupDto.getPassword())
                .name(useSignupDto.getName())
                .birth(useSignupDto.getBirth())
                .phone(useSignupDto.getPhone())
                .email(useSignupDto.getEmail())
                .gender(useSignupDto.getGender())
                .membershipLevel(useSignupDto.getMembershipLevel())
                .isLeave(useSignupDto.getIsLeave())
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
            userDtoUserGetAllDtoList.add(UserGetAllDto.builder()
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
        userEditDto.setIsLeave(false);

        return iUserRepository.save(User.builder()
                .id(userEditDto.getId())
                .userId(userEditDto.getUserId())
                .password(userEditDto.getPassword()) // (회원 정보 수정)
                .name(userEditDto.getName()) // (회원 정보 수정)
                .birth(userEditDto.getBirth()) // (회원 정보 수정)
                .phone(userEditDto.getPhone()) // (회원 정보 수정)
                .email(userEditDto.getEmail()) // (회원 정보 수정)
                .gender(userEditDto.getGender()) // (회원 정보 수정)
                .membershipLevel(userEditDto.getMembershipLevel())
                .isLeave(userEditDto.getIsLeave())
                .build());
    }

    @Override
    public User deleteUser(UserEditDto userEditDto) { // 회원 탈퇴
        userEditDto.setIsLeave(true);

        return iUserRepository.save(User.builder()
                .id(userEditDto.getId())
                .userId(userEditDto.getUserId())
                .password(userEditDto.getPassword())
                .name(userEditDto.getName())
                .birth(userEditDto.getBirth())
                .phone(userEditDto.getPhone())
                .email(userEditDto.getEmail())
                .gender(userEditDto.getGender())
                .membershipLevel(userEditDto.getMembershipLevel())
                .isLeave(userEditDto.getIsLeave()) // (회원 탈퇴)
                .build());
    }
}
