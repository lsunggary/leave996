package com.leave996.leave996.lombok;

import lombok.Cleanup;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Cleanup注解
 * 资源关闭
 */
public class CleanUpTest {

    public void copyFile(String in, String out)
        throws Exception {

            @Cleanup FileInputStream fileInputStream = new FileInputStream(in);
            @Cleanup FileOutputStream fileOutputStream = new FileOutputStream(out);

            int r;

            while ((r = fileInputStream.read()) != -1) {
                fileOutputStream.write(r);
            }

    }

    public void copyFile(String in, String out, String path) {

        try (
            FileInputStream fileInputStream = new FileInputStream(in);
            FileOutputStream fileOutputStream = new FileOutputStream(out)
        ) {
            int r;

            while ((r = fileInputStream.read()) != -1) {
                fileOutputStream.write(r);
            }
        } catch (FileNotFoundException file) {
            file.printStackTrace();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}
