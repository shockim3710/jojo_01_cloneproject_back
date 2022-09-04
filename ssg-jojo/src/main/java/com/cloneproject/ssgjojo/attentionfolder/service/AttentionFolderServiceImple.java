package com.cloneproject.ssgjojo.attentionfolder.service;

import com.cloneproject.ssgjojo.attention.domain.Attention;
import com.cloneproject.ssgjojo.attentionfolder.domain.AttentionFolder;
import com.cloneproject.ssgjojo.attentionfolder.dto.AttentionFolderAddDto;
import com.cloneproject.ssgjojo.attentionfolder.dto.AttentionFolderDeleteDto;
import com.cloneproject.ssgjojo.attentionfolder.dto.AttentionFolderEditDto;
import com.cloneproject.ssgjojo.attentionfolder.dto.AttentionFolderOutputDto;
import com.cloneproject.ssgjojo.attentionfolder.repository.IAttentionFolderRepository;
import com.cloneproject.ssgjojo.user.domain.User;
import com.cloneproject.ssgjojo.user.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttentionFolderServiceImple implements IAttentionFolderService{
    private final IAttentionFolderRepository iAttentionFolderRepository;
    private final IUserRepository iUserRepository;

    
    // 좋아요 폴더 추가
    @Override
    public AttentionFolderOutputDto addFolder(AttentionFolderAddDto addDto) {
        Optional<User> user = iUserRepository.findById(addDto.getUserId());
        if(user.isPresent()) {
            List<AttentionFolder> userFolderList = iAttentionFolderRepository.findAllByUserOrderByNo(user.get());
            int no = 0;

            if(userFolderList.size() != 0)
                no = userFolderList.get(userFolderList.size()-1).getNo() + 1;

            AttentionFolder folder =  iAttentionFolderRepository.save(AttentionFolder.builder()
                    .folderName(addDto.getFolderName())
                    .no(no)
                    .user(user.get())
                    .build());

            return AttentionFolderOutputDto.builder()
                    .id(folder.getId())
                    .no(folder.getNo())
                    .folderName(folder.getFolderName())
                    .userId(folder.getUser().getId())
                    .build();
        }

        return null;
    }

    // 유저별 좋아요 폴더 조회
    @Override
    public List<AttentionFolderOutputDto> findAllByUser(Long userId) {
        Optional<User> user = iUserRepository.findById(userId);

        if(user.isPresent()){
            List<AttentionFolder> folderList = iAttentionFolderRepository.findAllByUserOrderByNo(user.get());
            List<AttentionFolderOutputDto> returnDto = new ArrayList<>();

            for(AttentionFolder folder : folderList) {
                returnDto.add(AttentionFolderOutputDto.builder()
                        .id(folder.getId())
                        .folderName(folder.getFolderName())
                        .no(folder.getNo())
                        .userId(folder.getUser().getId())
                        .build());
            }

            return returnDto;
        }

        return null;
    }
    
    // 좋아요 폴더 수정
    @Override
    @Transactional
    public AttentionFolderOutputDto editFolder(AttentionFolderEditDto editDto) {
        Optional<AttentionFolder> folder = iAttentionFolderRepository.findById(editDto.getFolderId());
        Optional<User> user = iUserRepository.findById(editDto.getUserId());

        if(folder.isPresent() && user.isPresent()) {
            folder.get().setFolderName(editDto.getFolderName());

            return AttentionFolderOutputDto.builder()
                    .id(folder.get().getId())
                    .no(folder.get().getNo())
                    .folderName(folder.get().getFolderName())
                    .userId(folder.get().getUser().getId())
                    .build();
        }

        return null;
    }

    // 좋아요 폴더 삭제
    @Override
    @Transactional
    public void deleteFolderById(AttentionFolderDeleteDto deleteDto) {
        Optional<AttentionFolder> folder = iAttentionFolderRepository.findById(deleteDto.getFolderId());
        Optional<User> user = iUserRepository.findById(deleteDto.getUserId());

        // 0번은 기본 폴더로 삭제 불가능.
        if (folder.get().getNo() == 0)
            return;

        if(folder.isPresent() && user.isPresent()) {
            iAttentionFolderRepository.deleteById(folder.get().getId());
        }
    }


}
