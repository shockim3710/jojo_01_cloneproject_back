package com.cloneproject.ssgjojo.util.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.cloneproject.ssgjojo.util.MultipartUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Component
@RequiredArgsConstructor
public class AwsS3ResourceStorage {
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    private final AmazonS3 amazonS3;

    public void store(FileInfoDto fileInfoDto, MultipartFile multipartFile) {
        File local = new File(MultipartUtil.getBaseDir());

        if(!local.exists()) {
            local.mkdir();
        }

        File file = new File(fileInfoDto.getLocalPath());



        try {
            multipartFile.transferTo(file);

            amazonS3.putObject(bucket, fileInfoDto.getRemotePath(), file);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            if(file.exists())
                file.delete();
        }
    }
}
