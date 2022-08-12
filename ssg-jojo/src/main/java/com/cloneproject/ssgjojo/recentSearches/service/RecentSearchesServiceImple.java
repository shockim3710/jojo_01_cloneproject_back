package com.cloneproject.ssgjojo.recentSearches.service;

import com.cloneproject.ssgjojo.recentSearches.domain.RecentSearches;
import com.cloneproject.ssgjojo.recentSearches.dto.RecentSearchesAddDto;
import com.cloneproject.ssgjojo.recentSearches.dto.RecentSearchesDto;
import com.cloneproject.ssgjojo.recentSearches.repository.IRecentSearchesRepository;
import com.cloneproject.ssgjojo.user.domain.User;
import com.cloneproject.ssgjojo.user.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecentSearchesServiceImple implements IRecentSearchesService {

    private final IRecentSearchesRepository iRecentSearchesRepository;
    private final IUserRepository iUserRepository;

    @Override
    public RecentSearchesAddDto addRecentSearches(RecentSearchesAddDto recentSearchesAddDto) { // 최근검색어 추가
        Optional<User> user = iUserRepository.findById(recentSearchesAddDto.getUser());

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
    public List<RecentSearchesDto> getRecentSearchesByUserId(Long id) { // 해당 사용자의 최근검색어 조회
        Optional<User> userOptional = iUserRepository.findById(id);

        if(userOptional.isPresent()) {
            List<RecentSearches> recentSearchesList = iRecentSearchesRepository.findAllByUser(userOptional.get());
            List<RecentSearchesDto> recentSearchesDtoList = new ArrayList<>();

            recentSearchesList.forEach(user -> {
                recentSearchesDtoList.add(RecentSearchesDto.builder()
                        .id(user.getId())
                        .histories(user.getHistories())
                        .user(user.getUser().getId())
                        .build());
            });

            return recentSearchesDtoList;
        }

        return null;
    }

    @Override
    public void deleteRecentSearches(Long id) { // 해당 사용자의 최근검색어 삭제
        Optional<RecentSearches> recentSearches = iRecentSearchesRepository.findById(id);

        if(recentSearches.isPresent()) {
            iRecentSearchesRepository.deleteById(id);
        }
    }
}
