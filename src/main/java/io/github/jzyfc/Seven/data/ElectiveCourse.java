package io.github.jzyfc.Seven.data;

import io.github.jzyfc.Seven.serialization.ByteSerializable;
import io.github.jzyfc.Seven.serialization.SerializeUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ElectiveCourse implements Displayable, ByteSerializable<ElectiveCourse>, Modifiable {
    private String electiveID;
    private String studentID;
    private String classID;

    @Override
    public String toString() {
        return "选课号：%s, 学生号：%s, 课程号：%s".formatted(getElectiveID(), getStudentID(), getClassID());
    }

    public String getElectiveID() {
        return electiveID != null ? electiveID : "";
    }

    public void setElectiveID(String electiveID) {
        this.electiveID = electiveID;
    }

    public String getStudentID() {
        return studentID != null ? electiveID : "";
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getClassID() {
        return classID != null ? classID : "";
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
        SerializeUtils.serializeToBytes(getElectiveID(), base);
        SerializeUtils.serializeToBytes(getStudentID(), base);
        SerializeUtils.serializeToBytes(getClassID(), base);
        return base;
    }

    @Override
    public ElectiveCourse deserialize(ByteArrayInputStream base) throws IOException {
        this.electiveID = SerializeUtils.parseStringFromBytes(base);
        this.studentID = SerializeUtils.parseStringFromBytes(base);
        this.classID = SerializeUtils.parseStringFromBytes(base);
        return this;
    }

    @Override
    public int getDataCount() {
        return 3;
    }

    @Override
    public List<String> getDataHeader() {
        return List.of("选课号", "学生号", "课程号");
    }

    @Override
    public List<String> getDataRepresentation() {
        return List.of(getElectiveID(), getStudentID(), getClassID());
    }

    @Override
    public List<String> toStrings() {
        return getDataRepresentation();
    }

    @Override
    public void fromStrings(List<String> strings) {
        this.electiveID = strings.get(0);
        this.studentID = strings.get(1);
        this.classID = strings.get(2);
    }
}
