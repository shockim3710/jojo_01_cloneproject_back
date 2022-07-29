package com.busan.ssg.user.service;

import com.busan.ssg.user.domain.User;
import com.busan.ssg.user.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImple implements IUserService {

    private final IUserRepository iUserRepository;

    @Override
    public User addUser(User user) {
        log.info("{} added", user);
        return iUserRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {return iUserRepository.findById(id).get();
    }

    @Override
    public User editUser(User user) {
        return iUserRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        log.info("getAll User");
        return iUserRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) {iUserRepository.deleteById(id);}
}
