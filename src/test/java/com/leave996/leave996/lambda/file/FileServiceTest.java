package com.leave996.leave996.lambda.file;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class FileServiceTest {

    @Test
    public void fileHandle() throws IOException {
        FileService fileService = new FileService();

        fileService.fileHandle("D:\\workspace\\java-test\\" +
                        "leave996\\src\\test\\java\\" +
                        "com\\leave996\\leave996\\lambda\\" +
                        "file\\FileServiceTest.java",
                fileContent -> {
                    // 注释
                    System.out.println(fileContent);
                });
    }
}
