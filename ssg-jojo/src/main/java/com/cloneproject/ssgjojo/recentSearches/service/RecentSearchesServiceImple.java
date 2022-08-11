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
    public RecentSearchesAddDto addRecentSearches(RecentSearchesAddDto recentSearchesAddDto) {
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
    public List<RecentSearchesDto> getRecentSearchesByUserId(Long id) {
        Optional<User> user = iUserRepository.findById(id);

        if(user.isPresent()) {
            List<RecentSearches> recentSearchesList = iRecentSearchesRepository.findAllByUser(user.get());
            List<RecentSearchesDto> returnList = new ArrayList<>();

            for(RecentSearches temp : recentSearchesList) {
                returnList.add(
                        RecentSearchesDto.builder()
                                .id(temp.getId())
                                .histories(temp.getHistories())
                                .user(temp.getUser().getId())
                                .build()
                );
            }

            return returnList;
        }

        return null;
    }

    @Override
    public void deleteRecentSearches(Long id) {
        Optional<RecentSearches> recentSearches = iRecentSearchesRepository.findById(id);

        if(recentSearches.isPresent()) {
            iRecentSearchesRepository.deleteById(id);
        }
    }
}
