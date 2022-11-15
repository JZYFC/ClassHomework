package io.github.jzyfc.Seven;

import io.github.jzyfc.Seven.data.ElectiveCourse;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;

class ElectiveCourseTest {

    @Test
    void display() {
        ElectiveCourse electiveCourse = new ElectiveCourse("1", "1", "1");
        electiveCourse.display();
    }

    @Test
    @Order(1)
    void serialize() throws IOException {
        ElectiveCourse electiveCourse = new ElectiveCourse("1", "1", "1");
        var byteArrayOutputStream = electiveCourse.serialize(new ByteArrayOutputStream());
        var outputStream = new FileOutputStream("testOutput/out_electiveCourse.data");
        byteArrayOutputStream.writeTo(outputStream);
    }

    @Test
    @Order(2)
    void deserialize() throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Files.readAllBytes(new File("testOutput" +
                "/out_electiveCourse.data").toPath()));
        System.out.println(new ElectiveCourse().deserialize(byteArrayInputStream));
    }
}