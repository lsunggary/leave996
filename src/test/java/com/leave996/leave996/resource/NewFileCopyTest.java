package com.leave996.leave996.resource;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 实现JDK1.7以后的文件拷贝
 * try-with-resource
 */
public class NewFileCopyTest {

    @Test
    public void copyFile() {

        //定义输入输出路径
        String originalUrl = "lib/FileCopyTest.java";
        String targetUlr = "targetTest/newTartget.txt";

        //初始化输入/输出流对象
        try (
            FileInputStream originalFileInputStream =
                    new FileInputStream(originalUrl);

            FileOutputStream targetFilOutputStream =
                    new FileOutputStream(targetUlr);
        ) {
          int content;

          while((content = originalFileInputStream.read()) != -1) {
              targetFilOutputStream.write(content);
          }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
