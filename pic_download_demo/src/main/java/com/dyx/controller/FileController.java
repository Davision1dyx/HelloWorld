package com.dyx.controller;

import com.alibaba.fastjson.JSONObject;
import com.dyx.model.query.FileUploadQuery;
import com.dyx.utils.FileIOUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author Davison
 * @description DownloadController
 * @date 2023/1/23 0:16
 */
@RestController
@RequestMapping("/personalAssays/file")
@Slf4j
@Validated
public class FileController {

    private final static String PIC_FILE_PATH = "/home/pic/";

    @GetMapping("/download")
    public Object download(@RequestParam("id") @NotBlank(message = "id can't be blank") String id,
                      HttpServletResponse response) throws IOException {
        log.info("[GET] /personalAssays/File/download?id={}", id);
//        if (StringUtils.isBlank(id)) {
//            throw new IllegalArgumentException("id can't be blank");
//        }
        String filePath = new String(Base64.decodeBase64(id), "UTF-8");
        if (!filePath.startsWith("/home")) {
            throw new IllegalArgumentException();
        }

        if (filePath.endsWith(".jpg")) {
            response.setContentType("image/jpeg");
            response.setCharacterEncoding("UTF-8");
        } else {
            response.setContentType("application/octet-stream");
        }
        FileInputStream fos = new FileInputStream(filePath);
        ServletOutputStream outputStream = response.getOutputStream();

        byte[] cache = new byte[1024];
        while (fos.read(cache) != -1) {
        outputStream.write(cache);
        }
        outputStream.flush();
        return null;
    }

    @PostMapping("/uploadPic")
    public Object uploadPic(@RequestBody FileUploadQuery fileUploadQuery) throws IllegalAccessException {
        log.info("[GET] /personalAssays/File/upload, param: {}", JSONObject.toJSONString(fileUploadQuery));
        String imageData = fileUploadQuery.getImageData();
        String imageName = fileUploadQuery.getImageName();

        byte[] imageBytes = Base64.decodeBase64(imageData);
        Boolean isSuccess = FileIOUtils.write(imageBytes, PIC_FILE_PATH + imageName + ".jpg");
        if (!isSuccess) {
            throw new IllegalAccessException("upload picture error");
        }
        return null;
    }
}
