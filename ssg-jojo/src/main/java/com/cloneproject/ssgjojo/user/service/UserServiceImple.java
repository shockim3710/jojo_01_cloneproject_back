package com.cloneproject.ssgjojo.user.service;

import com.cloneproject.ssgjojo.attentionfolder.domain.AttentionFolder;
import com.cloneproject.ssgjojo.attentionfolder.repository.IAttentionFolderRepository;
import com.cloneproject.ssgjojo.deliveryaddress.domain.DeliveryAddress;
import com.cloneproject.ssgjojo.deliveryaddress.repository.IDeliveryAddressRepository;
import com.cloneproject.ssgjojo.jwt.JwtTokenProvider;
import com.cloneproject.ssgjojo.loginhistory.domain.LogInHistory;
import com.cloneproject.ssgjojo.loginhistory.repository.ILogInHistoryRepository;
import com.cloneproject.ssgjojo.user.domain.Role;
import com.cloneproject.ssgjojo.user.domain.User;
import com.cloneproject.ssgjojo.user.dto.*;
import com.cloneproject.ssgjojo.user.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImple implements IUserService{

    private final IUserRepository iUserRepository;
    private final IDeliveryAddressRepository iDeliveryAddressRepository;
    private final IAttentionFolderRepository iAttentionFolderRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final ILogInHistoryRepository iLogInHistoryRepository;

    @Override
    public String addUser(UserSignupDto userSignupDto) { // 회원가입

        userSignupDto.setIsLeave(false);
        userSignupDto.setMembershipLevel("Friends");
        userSignupDto.setWhetherSnsSignUp(false);
        userSignupDto.setRole(Role.USER);

        /*회원 비밀번호를 암호화 인코딩*/
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        /*비밀번호 암호화하여 다시 user객체에 저장.*/
        String securePw = encoder.encode(userSignupDto.getPassword());
        userSignupDto.setPassword(securePw);

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
                .role(userSignupDto.getRole())
                .build());

        userSignupDto.setWhetherDefaultAddress(true);
        userSignupDto.setWhetherOnlyThisTime(false);

        iDeliveryAddressRepository.save(DeliveryAddress.builder()
                        .user(user)
                        .address(userSignupDto.getAddress())
                        .whetherDefaultAddress(userSignupDto.isWhetherDefaultAddress())
                        .whetherOnlyThisTime(userSignupDto.isWhetherOnlyThisTime())
                        .build());

        userSignupDto.setFolderName("전체보기");

        iAttentionFolderRepository.save(AttentionFolder.builder()
                        .user(user)
                        .folderName(userSignupDto.getFolderName())
                .build());

        return "환영합니다.";
    }

    @Override
    public String getUserSignUpId(UserSignupDto userSignupDto) { // 아이디 중복확인

        Optional<User> user = iUserRepository.findByUserId(userSignupDto.getUserId());

        if(user.isPresent()) {
            return "이미 가입한 아이디입니다.";
        }

        return null;
    }

//    @Override
//    public User addKakaoUser(UserKakaoSignupDto userKakaoSignupDto) { // 카카오로 회원가입
//        userKakaoSignupDto.setIsLeave(false);
//        userKakaoSignupDto.setMembershipLevel("Friends");
//        userKakaoSignupDto.setWhetherSnsSignUp(true);
//
//        User user = iUserRepository.save(User.builder()
//                .userId(userKakaoSignupDto.getEmail())
//                .name(userKakaoSignupDto.getName())
//                .birth(userKakaoSignupDto.getBirth())
//                .phone(userKakaoSignupDto.getPhone())
//                .email(userKakaoSignupDto.getEmail())
//                .gender(userKakaoSignupDto.getGender())
//                .membershipLevel(userKakaoSignupDto.getMembershipLevel())
//                .isLeave(userKakaoSignupDto.getIsLeave())
//                .whetherSnsSignUp(userKakaoSignupDto.getWhetherSnsSignUp())
//                .build());
//
//        userKakaoSignupDto.setWhetherDefaultAddress(true);
//        userKakaoSignupDto.setWhetherOnlyThisTime(false);
//
//        iDeliveryAddressRepository.save(DeliveryAddress.builder()
//                .user(user)
//                .address(userKakaoSignupDto.getAddress())
//                .whetherDefaultAddress(userKakaoSignupDto.isWhetherDefaultAddress())
//                .whetherOnlyThisTime(userKakaoSignupDto.isWhetherOnlyThisTime())
//                .build());
//
//                userKakaoSignupDto.setFolderName("전체보기");
//
//        iAttentionFolderRepository.save(AttentionFolder.builder()
//                .user(user)
//                .folderName(userKakaoSignupDto.getFolderName())
//                .build());
//
//        return user;
//    }

    @Override
    public UserGetIdDto getUserById(HttpServletRequest request) { // 마이페이지
        // Request의 Header에서 token 값을 가져옵니다. "Authorization" : "TOKEN값'
        // 토큰에서 회원 정보 추출
        String userId = jwtTokenProvider.getUserPk(jwtTokenProvider.resolveToken(request));
        User user = iUserRepository.findById(Long.valueOf(userId)).orElseThrow();

        if(user != null && user.getIsLeave() == false) {
            return UserGetIdDto.builder()
                    .name(user.getName())
                    .membershipLevel(user.getMembershipLevel())
                    .build();
        }

        return null;
    }

    @Override
    public String getUserLogin(UserLoginDto userLoginDto) { // 로그인

        User userId = iUserRepository.findByUserId(userLoginDto.getUserId()).orElseThrow();

        //회원가입했는지 비교, 넘겨받은 비밀번호와 암호화된 비밀번호와 비교, 탈퇴여부 비교, SNS 회원가입인지 비교
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if(userId != null && encoder.matches(userLoginDto.getPassword(), userId.getPassword()) &&
                userId.getWhetherSnsSignUp() == false && userId.getIsLeave() == false) {

            iLogInHistoryRepository.save(LogInHistory.builder()
                    .user(userId)
                    .logInTime(new Timestamp(System.currentTimeMillis()))
                    .logInIp(userLoginDto.getLogInIp())
                    .build());

            return jwtTokenProvider.createToken(userId.getId(), userId.getRole());

        }

        return null;
    }

    @Override
    public String editUser(UserEditGetAllDto userEditDto, HttpServletRequest request) { // 회원 정보 수정

        // Request의 Header에서 token 값을 가져옵니다. "Authorization" : "TOKEN값'
        // 토큰에서 회원 정보 추출
        String userId = jwtTokenProvider.getUserPk(jwtTokenProvider.resolveToken(request));
        User user = iUserRepository.findById(Long.valueOf(userId)).orElseThrow();

        userEditDto.setIsLeave(false);
        userEditDto.setMembershipLevel("Friends");
        userEditDto.setWhetherSnsSignUp(false);
        userEditDto.setRole(Role.USER);

        /*회원 비밀번호를 암호화 인코딩*/
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        /*비밀번호 암호화하여 다시 user객체에 저장.*/
        String securePw = encoder.encode(userEditDto.getPassword());
        userEditDto.setPassword(securePw);

        if(user != null && user.getIsLeave() == false) {
            iUserRepository.save(User.builder()
                    .id(Long.valueOf(userId))
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
                    .role(userEditDto.getRole())
                    .build());

            return jwtTokenProvider.createToken(Long.valueOf(userId), userEditDto.getRole());
        }

        return null;
    }

    @Override
    @Transactional
    public String deleteUser(HttpServletRequest request) { // 회원 탈퇴
        String userId = jwtTokenProvider.getUserPk(jwtTokenProvider.resolveToken(request));
        Optional<User> user = iUserRepository.findById(Long.valueOf(userId));

        if(user.isPresent()) {
            user.get().setIsLeave(true);
            return "탈퇴하였습니다.";
        }
        return null;


        /*
         * ExceptionHandler를 활용해서 서비스에서 던져주면 컨트롤러에서 처리할 수 있음
         */
        /*return iUserRepository.save(User.builder()
                .isLeave(userEditDto.getIsLeave()) // (회원 탈퇴)
                .build());*/
    }


//    @Override
//    public List<UserEditGetAllDto> getAll() { // 전체 회원 조회
//        List<User> userList = iUserRepository.findAll();
//        List<UserEditGetAllDto> userDtoUserGetAllDtoList = new ArrayList<>();
//
//        userList.forEach( user -> {
//            userDtoUserGetAllDtoList.add(UserEditGetAllDto.builder()
//                    .userId(user.getUserId())
//                    .id(user.getId())
//                    .birth(user.getBirth())
//                    .gender(user.getGender())
//                    .email(user.getEmail())
//                    .name(user.getName())
//                    .password(user.getPassword())
//                    .phone(user.getPhone())
//                    .membershipLevel(user.getMembershipLevel())
//                    .isLeave(user.getIsLeave())
//                    .createTime((user.getCreatedDate()))
//                    .whetherSnsSignUp(user.getWhetherSnsSignUp())
//                    .build());
//                }
//        );
//
//        return userDtoUserGetAllDtoList;
//    }
}
