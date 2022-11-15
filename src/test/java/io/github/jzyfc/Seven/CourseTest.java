package io.github.jzyfc.Seven;

import io.github.jzyfc.Seven.data.Course;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;

class CourseTest {

    @Test
    void display() {
        Course course = new Course("1", "编译原理", 96);
        course.display();
    }

    @Test
    @Order(1)
    void serialize() throws IOException {
        Course course = new Course("1", "编译原理", 96);
        var byteArrayOutputStream = course.serialize(new ByteArrayOutputStream());
        var outputStream = new FileOutputStream("testOutput/out_course.data");
        byteArrayOutputStream.writeTo(outputStream);
    }

    @Test
    @Order(2)
    void deserialize() throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Files.readAllBytes(new File("testOutput" +
                "/out_course.data").toPath()));
        System.out.println(new Course().deserialize(byteArrayInputStream));
    }
}