package com.dyx.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Davison
 * @description FileIOUtils
 * @date 2023/1/23 11:32
 */
@Slf4j
public class FileIOUtils {

    public static Boolean write(byte[] imageBytes, String filePath) {

        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            fos.write(imageBytes);
            fos.flush();
            return true;
        } catch (IOException e) {
            log.error("file out stream error", e);
        }
        return false;
    }

    public static void read() {

    }
}
