package com.cloneproject.ssgjojo.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Component
public class MultipartUtil {
    private static final String BASE_DIR = System.getProperty("user.home") + "/temp";


    private static String bucket;

    @Value("${cloud.aws.s3.bucket}")
    public void setBucket( String value) {
        MultipartUtil.bucket = value;
    }


    private static String region;

    @Value("${cloud.aws.region.static}")
    public void setRegion(String value) {
        MultipartUtil.region = value;
    }


    /**
     * 파일에 대한 고유의 ID 발급
     * @return 36자리의 UUID
     */
    public static String createFileUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * Multipart의 ContentType 값에서 / 이후 확장자만 잘라냄
     * @param contentType ex) img/png
     * @return ex)png
     */
    public static String getFormat(String contentType) {
        if(StringUtils.hasText(contentType)) {
            return contentType.substring(contentType.lastIndexOf('/') + 1);
        }
        else
            throw new IllegalArgumentException( "잘못된 형식의 파일 입니다.");
    }

    /**
     * 로컬 저장 경로 지정
     * @param fileId
     * @param format
     * @return 
     */
    public static String createLocalPath(String fileId, String format) {
        return String.format("%s/imgs/%s.%s", BASE_DIR, fileId, format);
    }

    /**
     * 원격 저장 경로 지정
     * @param photoDiv
     * @param productId
     * @param fileId
     * @param format
     * @return
     */
    public static String createRemotePath(String photoDiv, Long productId, String fileId, String format) {
        return String.format("%s/%d/%s.%s", photoDiv, productId, fileId, format);
    }

    public static String createRemotePath(String photoDiv, String fileId, String format) {
        return String.format("%s/%s.%s", photoDiv, fileId, format);
    }

    public static String createURL(String remotePath) {
        return String.format("https://%s.s3.%s.amazonaws.com/%s", bucket, region, remotePath);
    }
}
