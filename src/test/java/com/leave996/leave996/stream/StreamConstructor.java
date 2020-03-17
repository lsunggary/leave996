package com.leave996.leave996.stream;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collector;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 流的四种构建形式
 */
public class StreamConstructor {

    /**
     * 直接构建流
     */
    @Test
    public void streamFromValue() {
        Stream stream = Stream.of(1, 2, 3, 4, 5);

        stream.forEach(System.out::println);
    }

    /**
     * 通过数组生成stream流
     */
    @Test
    public void streamFromArray() {
        int[] arrays = {1,2,3,4,5};

        IntStream stream = Arrays.stream(arrays);
        stream.forEach(System.out::println);
    }

    /**
     * 通过文件生成stream流
     * @throws IOException
     */
    @Test
    public void streamFromFile() throws IOException {
        Stream<String> stream = Files.lines(Paths.get("D:\\workspace\\java-test\\leave996\\src\\test\\java\\com\\leave996\\leave996\\lambda\\file\\FileServiceTest.java"));
        stream.forEach(System.out::println);
    }

    /**
     * 通过函数生成流，无限流。通过limit限制生成个数
     */
    @Test
    public void streamFromFunction() {
        // 从0开始+2累加无限生成流
//        Stream stream = Stream.iterate(0,n -> n + 2);

        Stream stream = Stream.generate(Math::random);
        stream.limit(100)
            .forEach(System.out::println);
    }
}
