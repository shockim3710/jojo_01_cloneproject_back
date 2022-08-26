package com.cloneproject.ssgjojo.user.service;

import com.cloneproject.ssgjojo.deliveryaddress.domain.DeliveryAddress;
import com.cloneproject.ssgjojo.deliveryaddress.repository.IDeliveryAddressRepository;
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
    private final IDeliveryAddressRepository iDeliveryAddressRepository;
//    private final IAttentionFolderRepository iAttentionFolderRepository;

    public User addUser(UserSignupDto userSignupDto) { // 회원가입

        userSignupDto.setIsLeave(false);
        userSignupDto.setMembershipLevel("Friends");
        userSignupDto.setWhetherSnsSignUp(false);

//        userSignupDto.setName("ssd");
//        userSignupDto.setPassword("ssd");
//        userSignupDto.setPhone("ssd");
//        userSignupDto.setEmail("ssd");
//        userSignupDto.setGender("ssd");
//        userSignupDto.setUserId("ssd");
//        userSignupDto.setAddress("ssd");



        User user = iUserRepository.save(User.builder()
                .userId(userSignupDto.getUserId())
                .password(userSignupDto.getPassword())
                .name(userSignupDto.getName())
                .birth(userSignupDto.getBirth())
                .phone(userSignupDto.getPhone())
                .email(userSignupDto.getEmail())
                .gender(userSignupDto.getGender())
                .membershipLevel(userSignupDto.getMembershipLevel())
                .isLeave(userSignupDto.getIsLeave())
                .whetherSnsSignUp(userSignupDto.getWhetherSnsSignUp())
                .build());

        userSignupDto.setWhetherDefaultAddress(true);
        userSignupDto.setWhetherOnlyThisTime(false);

        iDeliveryAddressRepository.save(DeliveryAddress.builder()
                .user(user)
                .address(userSignupDto.getAddress())
                .whetherDefaultAddress(userSignupDto.isWhetherDefaultAddress())
                .whetherOnlyThisTime(userSignupDto.isWhetherOnlyThisTime())
                .build());

//        userSignupDto.setFolderName("전체보기");
//
//        iAttentionFolderRepository.save(AttentionFolder.builder()
//                .user(user)
//                .folderName(userSignupDto.getFolderName())
//                .build());

        return user;
    }

    @Override
    public User addKakaoUser(UserKakaoSignupDto userKakaoSignupDto) {
        userKakaoSignupDto.setIsLeave(false);
        userKakaoSignupDto.setMembershipLevel("Friends");
        userKakaoSignupDto.setWhetherSnsSignUp(true);

        User user = iUserRepository.save(User.builder()
                .userId(userKakaoSignupDto.getEmail())
                .name(userKakaoSignupDto.getName())
                .birth(userKakaoSignupDto.getBirth())
                .phone(userKakaoSignupDto.getPhone())
                .email(userKakaoSignupDto.getEmail())
                .gender(userKakaoSignupDto.getGender())
                .membershipLevel(userKakaoSignupDto.getMembershipLevel())
                .isLeave(userKakaoSignupDto.getIsLeave())
                .whetherSnsSignUp(userKakaoSignupDto.getWhetherSnsSignUp())
                .build());

        userKakaoSignupDto.setWhetherDefaultAddress(true);
        userKakaoSignupDto.setWhetherOnlyThisTime(false);

        iDeliveryAddressRepository.save(DeliveryAddress.builder()
                .user(user)
                .address(userKakaoSignupDto.getAddress())
                .whetherDefaultAddress(userKakaoSignupDto.isWhetherDefaultAddress())
                .whetherOnlyThisTime(userKakaoSignupDto.isWhetherOnlyThisTime())
                .build());

        //        userKakaoSignupDto.setFolderName("전체보기");
//
//        iAttentionFolderRepository.save(AttentionFolder.builder()
//                .user(user)
//                .folderName(userKakaoSignupDto.getFolderName())
//                .build());

        return user;
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
                    .createTime((user.getCreatedDate()))
                    .whetherSnsSignUp(user.getWhetherSnsSignUp())
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
                    .whetherSnsSignUp(userEditDto.getWhetherSnsSignUp())
                    .build());
        }

        return null;
    }

    @Override
    public UserLoginDto getUserLogin(UserLoginDto userLoginDto) {
        User userIdAndPassword = iUserRepository.findByUserIdAndPassword(userLoginDto.getUserId(), userLoginDto.getPassword());

        if(userIdAndPassword != null && userIdAndPassword.getWhetherSnsSignUp() == false && userIdAndPassword.getIsLeave() == false) {

               return UserLoginDto.builder()
                       .id(userIdAndPassword.getId())
                       .userId(userIdAndPassword.getUserId())
                       .name(userIdAndPassword.getName())
                       .build();
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
