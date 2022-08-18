package com.cloneproject.ssgjojo.util.s3;

import com.cloneproject.ssgjojo.util.MultipartUtil;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileInfoDto {
    private String id;
    private String name;
    private String format;
    private String localPath;
    private String remotePath;
    private long bytes;

    @Builder.Default
    private LocalDateTime createTime = LocalDateTime.now();

    public static FileInfoDto multipartOf(MultipartFile multipartFile) {
        final String fileId = MultipartUtil.createFileUUID();
        final String fileName = multipartFile.getOriginalFilename();
        final String format =fileName.substring(fileName.lastIndexOf('.')+1);

        return FileInfoDto.builder()
                .id(fileId)
                .name(multipartFile.getOriginalFilename())
                .format(format)
                .localPath(MultipartUtil.createLocalPath(fileId, format))
                .bytes(multipartFile.getSize())
                .build();
    }

    public static FileInfoDto multipartOf(MultipartFile multipartFile, String photoDiv) {
        final String fileId = MultipartUtil.createFileUUID();
        final String fileName = multipartFile.getOriginalFilename();
        final String format = fileName.substring(fileName.lastIndexOf('.')+1);

        return FileInfoDto.builder()
                .id(fileId)
                .name(multipartFile.getOriginalFilename())
                .format(format)
                .localPath(MultipartUtil.createLocalPath(fileId, format))
                .remotePath(MultipartUtil.createRemotePath(photoDiv, fileId, format))
                .bytes(multipartFile.getSize())
                .build();
    }

    public static FileInfoDto multipartOf(MultipartFile multipartFile, String photoDiv, Long productId) {
        final String fileId = MultipartUtil.createFileUUID();
        final String fileName = multipartFile.getOriginalFilename();
        final String format = fileName.substring(fileName.lastIndexOf('.')+1);

        return FileInfoDto.builder()
                .id(fileId)
                .name(multipartFile.getOriginalFilename())
                .format(format)
                .localPath(MultipartUtil.createLocalPath(fileId, format))
                .remotePath(MultipartUtil.createRemotePath(photoDiv, productId, fileId, format))
                .bytes(multipartFile.getSize())
                .build();
    }
}
