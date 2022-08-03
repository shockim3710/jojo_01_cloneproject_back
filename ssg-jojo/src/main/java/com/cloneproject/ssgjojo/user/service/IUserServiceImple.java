package com.cloneproject.ssgjojo.user.service;

import com.cloneproject.ssgjojo.user.domain.User;
import com.cloneproject.ssgjojo.user.dto.UserDtoInput;
import com.cloneproject.ssgjojo.user.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class IUserServiceImple implements IUserService{

    private final IUserRepository iUserRepository;

    @Override
    public User addUser(UserDtoInput userDtoInput) {
//        log.info("{} added", user);
//        userDtoInput.setLeaveDate("2023");
        userDtoInput.setJoinDate("2022");
        userDtoInput.setMembershipLevel("Friends");
        return iUserRepository.save(User.builder()
                .userId(userDtoInput.getUserId())
                .password(userDtoInput.getPassword())
                .name(userDtoInput.getName())
                .birth(userDtoInput.getBirth())
                .phone(userDtoInput.getPhone())
                .email(userDtoInput.getEmail())
                .gender(userDtoInput.getGender())
//                .leaveDate(userDtoInput.getLeaveDate())
                .joinDate(userDtoInput.getJoinDate())
                .membershipLevel(userDtoInput.getMembershipLevel())
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
//                .leaveDate(user.getLeaveDate())
//                .joinDate(user.getJoinDate())
//                .membershipLevel("Friends")
//                .build();
//        return iUserRepository.save(user);
//    }




    @Override
    public User getUserById(Long id) {
        return iUserRepository.findById(id).get();
    }

    @Override
    public User editUser(User user) {
        return iUserRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        return iUserRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        iUserRepository.deleteById(id);

    }
}
