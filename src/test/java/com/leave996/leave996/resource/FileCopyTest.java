package com.leave996.leave996.resource;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * JDK7之前的文件拷贝
 */
public class FileCopyTest {

    @Test
    public void copyFile() {
        /**
         * 1.创建输入/输出流
         * 2.执行文件拷贝，读取文件内容，写入另一个文件
         * 3.关闭文件资源
         */
        //定义输入路径和输出路径
        String originalUrl = "lib/FileCopyTest.java";
        String targetUrl = "targetTest/target.java";

        FileInputStream originalFileInputStream = null;
        FileOutputStream targetFileOutputStream = null;

        try {
            // 实例化文件流对象
            originalFileInputStream = new FileInputStream(originalUrl);

            targetFileOutputStream = new FileOutputStream(targetUrl);

            //读取的字节星系
            int content;
            // 写入文件
            while((content = originalFileInputStream.read()) != -1) {
                targetFileOutputStream.write(content);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭流资源
            if (targetFileOutputStream != null) {
                try {
                    targetFileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (originalFileInputStream != null) {
                try {
                    originalFileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
