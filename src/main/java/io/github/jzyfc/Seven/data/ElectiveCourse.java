package io.github.jzyfc.Seven.data;

import io.github.jzyfc.Seven.Displayable;
import io.github.jzyfc.Seven.serialization.ByteSerializable;
import io.github.jzyfc.Seven.serialization.SerializeUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ElectiveCourse implements Displayable, ByteSerializable<ElectiveCourse> {
    private String electiveID;
    private String studentID;
    private String classID;

    @Override
    public String toString() {
        return "选课号：%s, 学生号：%s, 课程号：%s".formatted(electiveID, studentID, classID);
    }

    public String getElectiveID() {
        return electiveID;
    }

    public void setElectiveID(String electiveID) {
        this.electiveID = electiveID;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getClassID() {
        return classID;
    }

    public void setClassID(String classID) {
        this.classID = classID;
    }

    public ElectiveCourse() {
        this.electiveID = this.studentID = this.classID = null;
    }

    public ElectiveCourse(String electiveID, String studentID, String classID) {
        this.electiveID = electiveID;
        this.studentID = studentID;
        this.classID = classID;
    }

    @Override
    public ByteArrayOutputStream serialize(ByteArrayOutputStream base) throws IOException {
        SerializeUtils.serializeToBytes(this.electiveID, base);
        SerializeUtils.serializeToBytes(this.studentID, base);
        SerializeUtils.serializeToBytes(this.classID, base);
        return base;
    }

    @Override
    public ElectiveCourse deserialize(ByteArrayInputStream base) throws IOException {
        this.electiveID = SerializeUtils.parseStringFromBytes(base);
        this.studentID = SerializeUtils.parseStringFromBytes(base);
        this.classID = SerializeUtils.parseStringFromBytes(base);
        return this;
    }
}
