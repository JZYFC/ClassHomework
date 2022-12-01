package io.github.jzyfc.Seven.data;

import io.github.jzyfc.Seven.serialization.ByteSerializable;
import io.github.jzyfc.Seven.serialization.SerializeUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class Schedule implements Displayable, ByteSerializable<Schedule>, Modifiable {
    private String scheduleID;
    private String courseID;
    private String teacherID;
    private String classroom;

    @Override
    public String toString() {
        return "班级名：%s, 课程名：%s, 教师名：%s, 教室：%s".formatted(getScheduleID(), getCourseID(), getTeacherID(), getClassroom());
    }

    public String getScheduleID() {
        return scheduleID != null ? scheduleID : "";
    }

    public void setScheduleID(String scheduleID) {
        this.scheduleID = scheduleID;
    }

    public String getCourseID() {
        return courseID != null ? courseID : "";
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getTeacherID() {
        return teacherID != null ? teacherID : "";
    }

    public void setTeacherID(String teacherID) {
        this.teacherID = teacherID;
    }

    public String getClassroom() {
        return classroom != null ? classroom : "";
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public Schedule() {
        this.scheduleID = this.courseID = this.teacherID = this.classroom = null;
    }

    public Schedule(String scheduleID, String courseID, String teacherID, String classroom) {
        this.scheduleID = scheduleID;
        this.courseID = courseID;
        this.teacherID = teacherID;
        this.classroom = classroom;
    }

    @Override
    public ByteArrayOutputStream serialize(ByteArrayOutputStream base) throws IOException {
        SerializeUtils.serializeToBytes(getScheduleID(), base);
        SerializeUtils.serializeToBytes(getCourseID(), base);
        SerializeUtils.serializeToBytes(getTeacherID(), base);
        SerializeUtils.serializeToBytes(getClassroom(), base);
        return base;
    }

    @Override
    public Schedule deserialize(ByteArrayInputStream base) throws IOException {
        this.scheduleID = SerializeUtils.parseStringFromBytes(base);
        this.courseID = SerializeUtils.parseStringFromBytes(base);
        this.teacherID = SerializeUtils.parseStringFromBytes(base);
        this.classroom = SerializeUtils.parseStringFromBytes(base);
        return this;
    }

    @Override
    public int getDataCount() {
        return 4;
    }

    @Override
    public List<String> getDataHeader() {
        return List.of("班级名", "课程名", "教师名", "教室");
    }

    @Override
    public List<String> getDataRepresentation() {
        return List.of(getScheduleID(), getCourseID(), getTeacherID(), getClassroom());
    }

    @Override
    public List<String> toStrings() {
        return getDataRepresentation();
    }

    @Override
    public void fromStrings(List<String> strings) {
        this.scheduleID = strings.get(0);
        this.courseID = strings.get(1);
        this.teacherID = strings.get(2);
        this.classroom = strings.get(3);
    }
}
