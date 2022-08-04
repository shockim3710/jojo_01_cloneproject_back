package com.cloneproject.ssgjojo.user.service;

import com.cloneproject.ssgjojo.user.domain.User;
import com.cloneproject.ssgjojo.user.dto.UserDtoInput;
import com.cloneproject.ssgjojo.user.dto.UserDtoOutput;
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
    public User addUser(UserDtoInput userDtoInput) { // 회원가입
//        log.info("{} added", user);

        userDtoInput.setIsLeave(false);
        userDtoInput.setMembershipLevel("Friends");

        return iUserRepository.save(User.builder()
//                .id(userDtoInput.getId())
                .userId(userDtoInput.getUserId())
                .password(userDtoInput.getPassword())
                .name(userDtoInput.getName())
                .birth(userDtoInput.getBirth())
                .phone(userDtoInput.getPhone())
                .email(userDtoInput.getEmail())
                .gender(userDtoInput.getGender())
                .membershipLevel(userDtoInput.getMembershipLevel())
                .isLeave(userDtoInput.getIsLeave())
                .build());
    }

//    @Override
//    public User addUser(User user) {
//        log.info("{} added", user);
//        user.setLeaveDate("2023");
//        user.setJoinDate("2023");
//        User myUser = User.builder()
//                .userId(user.getUserId())
//                .password(user.getPassword())
//                .name(user.getName())
//                .birth(user.getBirth())
//                .phone(user.getPhone())
//                .email(user.getEmail())
//                .gender(user.getGender())
//                .membershipLevel("Friends")
//                .build();
//        return iUserRepository.save(user);
//    }

    @Override
    public UserDtoOutput getUserById(Long id) { // 마이페이지

        User user = iUserRepository.findById(id).get();
        return UserDtoOutput.builder()
                .userId(user.getUserId())
//                .password(user.getPassword())
                .name(user.getName())
                .birth(user.getBirth())
                .phone(user.getPhone())
                .email(user.getEmail())
                .gender(user.getGender())
                .membershipLevel(user.getMembershipLevel())
                .build();
    }

    @Override
    public List<UserDtoOutput> getAll() { // 회원 조회
        List<User> userList = iUserRepository.findAll();
        List<UserDtoOutput> userDtoOutputList = new ArrayList<>();

        userList.forEach( user -> {
            userDtoOutputList.add(UserDtoOutput
                    .builder()
                    .userId(user.getUserId())
                            .birth(user.getBirth())
                            .gender(user.getGender())
                            .email(user.getEmail())
                            .name(user.getName())
//                            .password(user.getPassword())
                            .phone(user.getPhone())
                            .membershipLevel(user.getMembershipLevel())
                    .build());
                }
        );

        return userDtoOutputList;
    }





    @Override
    public User editUser(UserDtoOutput userDtoOutput) { // 회원 정보 수정

        return iUserRepository.save(User.builder()
                .id(userDtoOutput.getId())
                .userId(userDtoOutput.getUserId())
//                .password(userDtoOutput.getPassword())
                .name(userDtoOutput.getName())
                .birth(userDtoOutput.getBirth())
                .phone(userDtoOutput.getPhone())
                .email(userDtoOutput.getEmail())
                .gender(userDtoOutput.getGender())
                .build());
    }

    @Override
    public User deleteUser(UserDtoInput userDtoInput) { // 회원 탈퇴

        userDtoInput.setIsLeave(true);

        return iUserRepository.save(User.builder()
                .isLeave(userDtoInput.getIsLeave())
                .build());
    }
}
