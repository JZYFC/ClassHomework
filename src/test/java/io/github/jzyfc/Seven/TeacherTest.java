package io.github.jzyfc.Seven;

import io.github.jzyfc.Seven.data.Teacher;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;

class TeacherTest {

    @Test
    void display() {
        Teacher teacher = new Teacher("1", "张三", "男", 18, "教授");
        teacher.display();
    }

    @Test
    @Order(1)
    void serialize() throws IOException {
        Teacher teacher = new Teacher("1", "张三", "男", 18, "教授");
        var byteArrayOutputStream = teacher.serialize(new ByteArrayOutputStream());
        var outputStream = new FileOutputStream("testOutput/out_teacher.data");
        byteArrayOutputStream.writeTo(outputStream);
    }

    @Test
    @Order(2)
    void deserialize() throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Files.readAllBytes(new File("testOutput" +
                "/out_teacher.data").toPath()));
        System.out.println(new Teacher().deserialize(byteArrayInputStream));
    }
}