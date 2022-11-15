package io.github.jzyfc.Seven;

import io.github.jzyfc.Seven.data.Student;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;

class StudentTest {

    @Test
    void display() {
        Student student = new Student("1", "张三", "男", 18, "语文");
        student.display();
    }

    @Test
    @Order(1)
    void serialize() throws IOException {
        Student person = new Student("0001", "张三", "男", 18, "Software");
        var byteArrayOutputStream = person.serialize(new ByteArrayOutputStream());
        var outputStream = new FileOutputStream("testOutput/out_student.data");
        byteArrayOutputStream.writeTo(outputStream);
    }

    @Test
    @Order(2)
    void deserialize() throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Files.readAllBytes(new File("testOutput" +
                "/out_student.data").toPath()));
        System.out.println(new Student().deserialize(byteArrayInputStream));
    }
}