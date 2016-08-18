package com.zyx.utils;

import com.zyx.constants.Constants;
import com.zyx.file.FastDFSClient;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by SubDong on 2016/3/16.
 */
public class FileUploadUtils {
    //图片最大上传大小
    public static int IMAGES_MAX_BYTE = 5 * 1024 * 1024;


    /**
     * 文件上传
     *
     * @param file
     * @return
     */
    public static String uploadFile(MultipartFile file) {
        System.out.println("uploadFile method : " + file);

        String allFileName = file.getOriginalFilename();

        System.out.println("allFileName : " + allFileName);

        String fileName = getFileExtension(allFileName);

        System.out.println("fileName : " + fileName);
        fileName = fileName.toLowerCase();
        //"gif", "jpeg", "jpg", "bmp", "png"
        String[] strings = new String[]{"png", "gif", "jpeg", "jpg", "bmp"};//所有文件格式
        List<String> list = Arrays.asList(strings);
        try {
            if (list.contains(fileName)) {
                byte[] tempFile = file.getBytes();
                String[] images = new String[]{"png", "gif", "jpeg", "jpg", "bmp"};//可上传图片格式
                List<String> imagesList = Arrays.asList(images);
                if (imagesList.contains(fileName) && tempFile.length > IMAGES_MAX_BYTE) {
                    return Constants.AUTH_ERROR_901 + "";
                }

                String uploadFile = FastDFSClient.uploadFiles(tempFile, allFileName);
                if (uploadFile != null) {
                    return uploadFile;
                } else {
                    return Constants.AUTH_ERROR_902 + "";
                }
            } else {
                return Constants.AUTH_ERROR_903 + "";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return Constants.ERROR + "";
        }
    }

    public static Map<String, Object> uploadFile(MultipartFile[] file) {
        Map<String, Object> resultMap = new HashMap<>();
        List<String> resultDbPath = new ArrayList<>();
        for (MultipartFile f : file) {
            String allFileName = f.getOriginalFilename();


            String fileName = getFileExtension(allFileName);

            fileName = fileName.toLowerCase();
            //"gif", "jpeg", "jpg", "bmp", "png"
            String[] strings = new String[]{"png", "gif", "jpeg", "jpg", "bmp"};
            try {
                if (Arrays.binarySearch(strings, fileName) != -1) {


                    byte[] tempFile = f.getBytes();
                    String[] images = new String[]{"png", "gif", "jpeg", "jpeg", "jpg", "bmp"};
                    if (Arrays.binarySearch(images, fileName) != -1 && tempFile.length > IMAGES_MAX_BYTE) {
                        return MapUtils.buildErrorMap(Constants.AUTH_ERROR_901, Constants.AUTH_ERROR_901_MSG);

                    }

                    String uploadFile = FastDFSClient.uploadFiles(tempFile, allFileName);
                    if (uploadFile != null) {
                        resultDbPath.add(uploadFile);
                    } else {
                        return MapUtils.buildErrorMap(Constants.AUTH_ERROR_902, Constants.AUTH_ERROR_902_MSG);
                    }
                } else {
                    return MapUtils.buildErrorMap(Constants.AUTH_ERROR_903, Constants.AUTH_ERROR_903_MSG);
                }
            } catch (IOException e) {
                e.printStackTrace();
                return Constants.MAP_500;
            }
        }
        resultMap.put("dbFilePath", resultDbPath);
        return resultMap;
    }

    public static String getFileExtension(String fullName) {
        FileUploadUtils.checkNotNull(fullName);
        String fileName = (new File(fullName)).getName();
        int dotIndex = fileName.lastIndexOf(46);
        return dotIndex == -1 ? "" : fileName.substring(dotIndex + 1);
    }

    public static <T> T checkNotNull(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        } else {
            return reference;
        }
    }
}
