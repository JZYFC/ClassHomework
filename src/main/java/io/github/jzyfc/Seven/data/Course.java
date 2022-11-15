package io.github.jzyfc.Seven.data;

import io.github.jzyfc.Seven.Displayable;
import io.github.jzyfc.Seven.serialization.ByteSerializable;
import io.github.jzyfc.Seven.serialization.SerializeUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Course implements Displayable, ByteSerializable<Course> {
    private String courseName;
    private String courseID;
    private int courseHour;

    public Course() {
        this.courseName = null;
        this.courseID = null;
        this.courseHour = 0;
    }

    public Course(String courseName, String courseID, int courseHour) {
        this.courseName = courseName;
        this.courseID = courseID;
        this.courseHour = courseHour;
    }

    @Override
    public String toString() {
        return "课程名：%s, 课程ID：%s, 课时：%d".formatted(courseName, courseID, courseHour);
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public int getCourseHour() {
        return courseHour;
    }

    public void setCourseHour(int courseHour) {
        this.courseHour = courseHour;
    }

    @Override
    public ByteArrayOutputStream serialize(ByteArrayOutputStream base) throws IOException {
        SerializeUtils.serializeToBytes(this.courseName, base);
        SerializeUtils.serializeToBytes(this.courseID, base);
        SerializeUtils.serializeToBytes(this.courseHour, base);
        return base;
    }

    @Override
    public Course deserialize(ByteArrayInputStream base) throws IOException {
        this.courseName = SerializeUtils.parseStringFromBytes(base);
        this.courseID = SerializeUtils.parseStringFromBytes(base);
        this.courseHour = SerializeUtils.parseIntFromBytes(base);
        return this;
    }
}
