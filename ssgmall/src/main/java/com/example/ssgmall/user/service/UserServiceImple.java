package com.example.ssgmall.user.service;

import com.example.ssgmall.user.domain.User;
import com.example.ssgmall.user.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
// 로깅에 대한 추상 레이어를 제공하는 인터페이스의 모음, lombok 사용시 가능
@RequiredArgsConstructor
// final이나 @NonNull 인 필드 값만 파라미터로 받는 생성자 생성
@Service
public class UserServiceImple implements IUserService {
    private final IUserRepository iUserRepository;
    // final 필드 : 클래스에서 초기화를 하던지 객체 생성 시 생성자를 이용해 꼭 초기화해줘야 함

    @Override
    // 상속받은 메서드를 재정의할 때 메서드 이름이 틀리지 않게 쓰기 위한 어노테이션
    // 해당 어노테이션을 사용하면 컴파일러에게 부모 클래스에 있는 메서드명과 매개 변수 등이 동일한지 확인
    public User addUser(User user) { return iUserRepository.save(user); }

    @Override
    public User getUserById(Long id) { return iUserRepository.findById(id).get(); }

    @Override
    public User editUser(User user) { return iUserRepository.save(user); }

    @Override
    public List<User> getAll() {
        log.info("getAll User");
        return iUserRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) { iUserRepository.deleteById(id); }
}
