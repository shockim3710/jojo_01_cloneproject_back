package com.cloneproject.ssgjojo.kakaologin;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import com.cloneproject.ssgjojo.user.domain.User;
import com.cloneproject.ssgjojo.user.dto.UserKakaoSignupDto;
import com.cloneproject.ssgjojo.user.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@Slf4j
@RequiredArgsConstructor
@Service
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class KakaoController {

	KakaoService kakaoService = new KakaoService();
	private final IUserRepository iUserRepository;
//	private UserKakaoSignupDto userKakaoSignupDto;

	@RequestMapping(value="/login")
//	public ModelAndView login(@RequestBody(required = false) UserKakaoSignupDto userKakaoSignupDto,
//							  @RequestParam("code") String code, HttpSession session) {
	public HashMap<String, Object> login(@RequestParam("code") String code, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		// 1번 인증코드 요청 전달
		String accessToken = kakaoService.getAccessToken(code);
		// 2번 인증코드로 토큰 전달
		HashMap<String, Object> userInfo = kakaoService.getUserInfo(accessToken);

		// 접속자 정보 get
//		String nickname = (String) userInfo.get("nickname");
		String email = (String) userInfo.get("email");
		String birthday = (String) userInfo.get("birthday");
		String gender = (String) userInfo.get("gender");

		System.out.println("login info : " + userInfo.toString());

		User userId = iUserRepository.findByUserId(email);

		if(userId == null) {
			log.warn("카카오로 회원가입");

//			userKakaoSignupDto.setUserId(email);
//			userKakaoSignupDto.setEmail(email);
//			userKakaoSignupDto.setBirth(birthday);
//			userKakaoSignupDto.setGender(gender);
//
//			iUserRepository.save(User.builder()
//					.userId(userKakaoSignupDto.getUserId())
//					.name(userKakaoSignupDto.getName())
//					.birth(userKakaoSignupDto.getBirth())
//					.phone(userKakaoSignupDto.getPhone())
//					.email(userKakaoSignupDto.getEmail())
//					.gender(userKakaoSignupDto.getGender())
//					.membershipLevel(userKakaoSignupDto.getMembershipLevel())
//					.isLeave(userKakaoSignupDto.getIsLeave())
//					.build());

//
//			userKakaoSignupDto.setUserId(email);
//			userKakaoSignupDto.setEmail(email);
//			userKakaoSignupDto.setBirth(birthday);
//			userKakaoSignupDto.setGender(gender);

//			User user = iUserRepository.save(User.builder()
//					.userId(email)
//					.name("ksh")
//					.password("****")
//					.birth(birthday)
//					.phone("08080808")
//					.email(email)
//					.gender(gender)
//					.membershipLevel("th")
//					.isLeave(false)
//					.build());

			return userInfo;

		}

		log.warn("카카오로 로그인");
		if(userInfo.get("email") != null) {
			session.setAttribute("userId", userInfo.get("email"));
			session.setAttribute("accessToken", accessToken);
		}
		mav.addObject("userId", userInfo.get("email"));
		mav.setViewName("index");
		return null;
	}

	@RequestMapping(value="/logout")
	public ModelAndView logout(HttpSession session) {
		ModelAndView mav = new ModelAndView();

		kakaoService.kakaoLogout((String)session.getAttribute("accessToken"));
		session.removeAttribute("accessToken");
		session.removeAttribute("userId");
		mav.setViewName("index");
		return mav;
	}


}
