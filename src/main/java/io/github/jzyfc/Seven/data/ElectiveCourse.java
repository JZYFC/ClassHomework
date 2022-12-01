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
    private String scheduleID;

    @Override
    public String toString() {
        return "选课号：%s, 学生号：%s, 课程号：%s".formatted(getElectiveID(), getStudentID(), getScheduleID());
    }

    public String getElectiveID() {
        return electiveID != null ? electiveID : "";
    }

    public void setElectiveID(String electiveID) {
        this.electiveID = electiveID;
    }

    public String getStudentID() {
        return studentID != null ? studentID : "";
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getScheduleID() {
        return scheduleID != null ? scheduleID : "";
    }

    public void setScheduleID(String scheduleID) {
        this.scheduleID = scheduleID;
    }

    public ElectiveCourse() {
        this.electiveID = this.studentID = this.scheduleID = null;
    }

    public ElectiveCourse(String electiveID, String studentID, String scheduleID) {
        this.electiveID = electiveID;
        this.studentID = studentID;
        this.scheduleID = scheduleID;
    }

    @Override
    public ByteArrayOutputStream serialize(ByteArrayOutputStream base) throws IOException {
        SerializeUtils.serializeToBytes(getElectiveID(), base);
        SerializeUtils.serializeToBytes(getStudentID(), base);
        SerializeUtils.serializeToBytes(getScheduleID(), base);
        return base;
    }

    @Override
    public ElectiveCourse deserialize(ByteArrayInputStream base) throws IOException {
        this.electiveID = SerializeUtils.parseStringFromBytes(base);
        this.studentID = SerializeUtils.parseStringFromBytes(base);
        this.scheduleID = SerializeUtils.parseStringFromBytes(base);
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
        return List.of(getElectiveID(), getStudentID(), getScheduleID());
    }

    @Override
    public List<String> toStrings() {
        return getDataRepresentation();
    }

    @Override
    public void fromStrings(List<String> strings) {
        this.electiveID = strings.get(0);
        this.studentID = strings.get(1);
        this.scheduleID = strings.get(2);
    }
}
