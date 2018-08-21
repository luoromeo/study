package com.luoromeo.study.test.translations.api.design.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @description https://github.com/oldratlee/translations/blob/master/generic-io-api-in-java-and-api-design/README.md
 * @author zhanghua.luo
 * @date 2018年05月24日 10:44
 * @modified By
 */
public class LoadFile {

    public static void main(String[] args) throws IOException {
        File source = new File(LoadFile.class.getClass().getResource("/iotest.txt").getFile());
        File destination = File.createTempFile("test", "txt");
        destination.deleteOnExit();
        long count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
            BufferedWriter writer = new BufferedWriter(new FileWriter(destination));
            try {
                String line = null;
                while ((line = reader.readLine()) != null) {
                    count++;
                    writer.append(line).append("\n");
                }
                writer.close();
            } catch (IOException e) {
                writer.close();
                destination.delete();
            }
        }
        System.out.println(count);

    }
}
