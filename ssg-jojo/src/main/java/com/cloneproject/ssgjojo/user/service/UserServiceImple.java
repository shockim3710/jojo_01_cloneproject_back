package com.cloneproject.ssgjojo.user.service;

import com.cloneproject.ssgjojo.user.domain.User;
import com.cloneproject.ssgjojo.user.dto.*;
import com.cloneproject.ssgjojo.user.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImple implements IUserService{

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
        Optional<User> user = iUserRepository.findById(id);

        if(user.isPresent()) {
            return UserGetIdDto.builder()
                    .id(user.get().getId())
                    .userId(user.get().getUserId())
                    .password(user.get().getPassword())
                    .name(user.get().getName())
                    .birth(user.get().getBirth())
                    .phone(user.get().getPhone())
                    .email(user.get().getEmail())
                    .gender(user.get().getGender())
                    .membershipLevel(user.get().getMembershipLevel())
                    .build();
        }
        
        return null;
    }



    @Override
    public List<UserEditGetAllDto> getAll() { // 회원 조회
        List<User> userList = iUserRepository.findAll();
        List<UserEditGetAllDto> userDtoUserGetAllDtoList = new ArrayList<>();

        userList.forEach( user -> {
            userDtoUserGetAllDtoList.add(UserEditGetAllDto.builder()
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
    public User editUser(UserEditGetAllDto userEditDto) { // 회원 정보 수정

        Optional<User> user = iUserRepository.findById(userEditDto.getId());

        userEditDto.setIsLeave(false);

        if(user.isPresent()) {
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

        return null;
    }

    @Override
    @Transactional
    public User deleteUser(Long id) { // 회원 탈퇴
//        if(iUserRepository.findById(id).isEmpty())
//            throw new IllegalArgumentException("존재하지않는 유저입니다.");

        Optional<User> user = iUserRepository.findById(id);

        if(user.isPresent()) {
            user.get().setIsLeave(true);
            return user.get();
        }
        return null;


        /*
         * ExceptionHandler를 활용해서 서비스에서 던져주면 컨트롤러에서 처리할 수 있음
         */
        /*return iUserRepository.save(User.builder()
                .isLeave(userEditDto.getIsLeave()) // (회원 탈퇴)
                .build());*/
    }

}
