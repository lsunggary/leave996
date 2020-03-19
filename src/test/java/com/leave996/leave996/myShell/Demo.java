package com.leave996.leave996.myShell;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 获取静态资源文件列表
 */
public class Demo {

    private static String fileUrl = "D:\\workspace\\文档\\静安寺项目\\小程序3-5,四项制度 流程原型\\街道服务企业";

    @Test
    public void getFilenameList() {

        try {
            Map<String, Object> result;

            File file = new File(fileUrl);
            File[] files = file.listFiles();
            if (files == null) {
                return;
            }
            result = searchFiles(files);

            System.out.println(JSON.toJSONString(result, true));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Map<String, Object> searchFiles (File[] files) {
        List<String> fileNameList = new ArrayList<>();
        Map<String, Object> result = new HashMap<>();
        String currentDirectoryName = "";

        for (File file: files) {
            if (file.isDirectory()) {
                if ("".equals(currentDirectoryName)) {
                    currentDirectoryName = file.getName();
                }
                Map<String, Object> tempResult = searchFiles(file.listFiles());
                if (!"".equals(currentDirectoryName)) {
                    List<String> tempList = new ArrayList<String>((List<String>)tempResult.get("temp"));
                    result.put(currentDirectoryName, tempList);
                    fileNameList = new ArrayList<>();
                    currentDirectoryName = "";
                }
            }
            if (file.isFile()) {
                fileNameList.add(file.getName());
            }
        }
        if (result.size() == 0) {
            result.put("temp", fileNameList);
        }
        return result;
    }
}
