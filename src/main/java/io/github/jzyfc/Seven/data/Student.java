package io.github.jzyfc.Seven.data;

import io.github.jzyfc.Seven.serialization.SerializeUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Student extends Person {
    private String studentID;

    private String major;

    @Override
    public String toString() {
        return "学号：%s, %s, 专业：%s".formatted(studentID, super.toString(), major);
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Student() {
        super();
        this.studentID = null;
        this.major = null;
    }

    public Student(String studentID, String name, String sex, int age, String major) {
        super(name, sex, age);
        this.studentID = studentID;
        this.major = major;
    }

    @Override
    public ByteArrayOutputStream serialize(ByteArrayOutputStream base) throws IOException {
        // super
        super.serialize(base);
        // studentID
        SerializeUtils.serializeToBytes(this.studentID, base);
        // major
        SerializeUtils.serializeToBytes(this.major, base);
        return base;
    }

    @Override
    public Student deserialize(ByteArrayInputStream base) throws IOException {
        super.deserialize(base);
        // studentID
        this.studentID = SerializeUtils.parseStringFromBytes(base);
        // major
        this.major = SerializeUtils.parseStringFromBytes(base);
        return this;
    }
}
