package io.github.jzyfc.Seven.ui;

import io.github.jzyfc.Seven.data.*;

import javax.swing.*;
import java.awt.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class MainWindow extends JFrame {
    public MainWindow() throws HeadlessException {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setSize(800, 600);

        this.setTitle("管理系统");

        var teacherList =
                new ArrayList<>(List.of(
                        new Teacher("1", "张三", "男", 18, "教授"),
                        new Teacher("2", "李四", "男", 18, "教授"),
                        new Teacher("3", "王五", "男", 18, "教授")
                ));
        var studentList =
                new ArrayList<>(List.of(
                        new Student("0001", "张三", "男", 18, "Software"),
                        new Student("0002", "李四", "男", 18, "Software"),
                        new Student("0003", "王五", "男", 18, "Software")
                ));
        var courseList =
                new ArrayList<>(List.of(
                        new Course("1", "编译原理", 96),
                        new Course("2", "编译原", 96),
                        new Course("3", "编译", 96)
                ));
        var scheduleList =
                new ArrayList<>(List.of(
                        new Schedule("1", "1", "1", "103"),
                        new Schedule("2", "2", "2", "104"),
                        new Schedule("3", "3", "3", "105")

                ));
        var electiveList =
                new ArrayList<>(List.of(
                        new ElectiveCourse("1", "1", "1"),
                        new ElectiveCourse("2", "2", "2"),
                        new ElectiveCourse("3", "3", "3")
                ));

//        this.setLayout(new BorderLayout());
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add("教师信息", new DataPanel<>(this, "教师", teacherList, Teacher::new));
        tabbedPane.add("学生信息", new DataPanel<>(this, "学生", studentList, Student::new));
        tabbedPane.add("课程信息", new DataPanel<>(this, "课程", courseList, Course::new));
        tabbedPane.add("排课信息", new DataPanel<>(this, "排课", scheduleList, Schedule::new));
        tabbedPane.add("选课信息", new DataPanel<>(this, "选课", electiveList, ElectiveCourse::new));

        this.add(tabbedPane);
        this.setVisible(true);
    }
}
