package com.leave996.leave996.lambda.file;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 文件服务类
 */
public class FileService {

    /**
     *
     * @param url
     * @param fileConsumer
     * @throws IOException
     */
    public void fileHandle(String url, FileConsumer fileConsumer) throws IOException {
        // 创建文件读取流
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(url)
                )
        );
        //定义行变量和内容sb
        String line;
        StringBuilder stringBuffer = new StringBuilder();
        while ((line = bufferedReader.readLine()) != null) {
            stringBuffer.append(line + "\n");
        }

        fileConsumer.fileHandler(stringBuffer.toString());
    }
}
