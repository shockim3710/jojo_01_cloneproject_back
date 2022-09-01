package com.cloneproject.ssgjojo.recentsearches.service;

import com.cloneproject.ssgjojo.jwt.JwtTokenProvider;
import com.cloneproject.ssgjojo.recentsearches.domain.RecentSearches;
import com.cloneproject.ssgjojo.recentsearches.dto.RecentSearchesAddDto;
import com.cloneproject.ssgjojo.recentsearches.repository.IRecentSearchesRepository;
import com.cloneproject.ssgjojo.user.domain.User;
import com.cloneproject.ssgjojo.user.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecentSearchesServiceImple implements IRecentSearchesService {

    private final IRecentSearchesRepository iRecentSearchesRepository;
    private final IUserRepository iUserRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public RecentSearchesAddDto addRecentSearches(RecentSearchesAddDto recentSearchesAddDto, HttpServletRequest request) { // 최근검색어 추가
        Long userId = Long.valueOf(jwtTokenProvider.getUserPk(jwtTokenProvider.resolveToken(request)));
        Optional<User> user = iUserRepository.findById(userId);

        if(user.isPresent()) {
            RecentSearches temp = iRecentSearchesRepository.save(RecentSearches.builder()
                    .histories(recentSearchesAddDto.getHistories())
                    .user(user.get())
                    .build());

            return RecentSearchesAddDto.builder()
                    .histories(temp.getHistories())
                    .user(temp.getUser().getId())
                    .build();
        }

        return null;
    }

    @Override

    public List<String> getRecentSearchesByUserId(HttpServletRequest request) { // 해당 사용자의 최근검색어 조회
        Long userId = Long.valueOf(jwtTokenProvider.getUserPk(jwtTokenProvider.resolveToken(request)));
        Optional<User> user = iUserRepository.findById(userId);
        List<String> recentSearchesList = new ArrayList<>();


        if(user.isPresent()) {

            Pageable pr = PageRequest.of(0, 10, Sort.by("id").descending());
            recentSearchesList = iRecentSearchesRepository.findTop10ById(userId, pr);

            return recentSearchesList;
        }

        return null;
    }

    @Override
    @Transactional
    public void deleteRecentSearches(Long id, HttpServletRequest request) { // 해당 사용자의 최근검색어 삭제

        Long userId = Long.valueOf(jwtTokenProvider.getUserPk(jwtTokenProvider.resolveToken(request)));
        Optional<User> user = iUserRepository.findById(userId);
        Optional<RecentSearches> recentSearches = iRecentSearchesRepository.findById(userId);

        if(recentSearches.isPresent() && user.isPresent()) {
            iRecentSearchesRepository.deleteByIdAndUser(id, user.get());
        }
    }

    @Override
    @Transactional
    public void deleteAllByUser(HttpServletRequest request) {

        Long userId = Long.valueOf(jwtTokenProvider.getUserPk(jwtTokenProvider.resolveToken(request)));
        Optional<User> user = iUserRepository.findById(userId);

        if(user.isPresent()) {
            iRecentSearchesRepository.deleteAllByUser(user.get());
        }
    }


}
