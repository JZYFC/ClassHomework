package io.github.jzyfc.Seven.data;

import io.github.jzyfc.Seven.Displayable;
import io.github.jzyfc.Seven.serialization.ByteSerializable;
import io.github.jzyfc.Seven.serialization.SerializeUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Schedule implements Displayable, ByteSerializable<Schedule> {
    private String classID;
    private String courseID;
    private String teacherID;
    private String classroom;

    @Override
    public String toString() {
        return "班级名：%s, 课程名：%s, 教师名：%s, 教室：%s".formatted(classID, courseID, teacherID, classroom);
    }

    public String getClassID() {
        return classID;
    }

    public void setClassID(String classID) {
        this.classID = classID;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(String teacherID) {
        this.teacherID = teacherID;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public Schedule() {
        this.classID = this.courseID = this.teacherID = this.classroom = null;
    }

    public Schedule(String classID, String courseID, String teacherID, String classroom) {
        this.classID = classID;
        this.courseID = courseID;
        this.teacherID = teacherID;
        this.classroom = classroom;
    }

    @Override
    public ByteArrayOutputStream serialize(ByteArrayOutputStream base) throws IOException {
        SerializeUtils.serializeToBytes(this.classID, base);
        SerializeUtils.serializeToBytes(this.courseID, base);
        SerializeUtils.serializeToBytes(this.teacherID, base);
        SerializeUtils.serializeToBytes(this.classroom, base);
        return base;
    }

    @Override
    public Schedule deserialize(ByteArrayInputStream base) throws IOException {
        this.classID = SerializeUtils.parseStringFromBytes(base);
        this.courseID = SerializeUtils.parseStringFromBytes(base);
        this.teacherID = SerializeUtils.parseStringFromBytes(base);
        this.classroom = SerializeUtils.parseStringFromBytes(base);
        return this;
    }
}
